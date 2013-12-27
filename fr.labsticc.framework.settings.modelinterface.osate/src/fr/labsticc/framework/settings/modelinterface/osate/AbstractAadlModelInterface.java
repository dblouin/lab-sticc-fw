package fr.labsticc.framework.settings.modelinterface.osate;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.ModelUnit;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NumberValue;
import org.osate.aadl2.PackageSection;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.PropertySet;
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.RangeValue;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.properties.InvalidModelException;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;

import fr.labsticc.framework.settings.model.settings.AssociatedPropertyException;
import fr.labsticc.framework.settings.model.settings.impl.ModelInterfaceImpl;

/**
 * This is an abstract model interface for AADL to be extended by concrete model interfaces. It has no dependency on UI 
 * elements so that it can be used for various application contexts (standalone, IDE integrated, etc.). This model interface
 * is also responsible for providing the scope for a given model element. This scope consists of all elements visible in 
 * the context of the specified element.
 * 
 * @author dblouin
 */
public abstract class AbstractAadlModelInterface extends ModelInterfaceImpl {
	
	protected Set<String> aadlExtensions;

	protected AbstractAadlModelInterface( final String... p_aadlExtensions ) {
		aadlExtensions = new HashSet<String>();
		aadlExtensions.addAll( Arrays.asList( p_aadlExtensions ) );
	}
	
	@Override
	public Number getPropertyValue( final Object p_component,
									final Object p_aadlProperty,
									final Object p_expectedUnit ) 
	throws AssociatedPropertyException {
		final Property propDef = (Property) p_aadlProperty;
		final PropertyValue value = getPropertyValue( p_component, propDef );
		
		if ( value == null ) {
			return null;
		}
		
		final UnitLiteral assUnit = (UnitLiteral) p_expectedUnit;

		if ( value instanceof NumberValue ) {
			return value( propDef, (NumberValue) value, assUnit );
		}
		
		if ( value instanceof RangeValue ) {
			return value( propDef, (RangeValue) value, assUnit );
		}
		
		throw new AssociatedPropertyException( getLanguageName() + " property " + propDef.getName() + " is not of a numerical type." );
	}
	
	private Double value( 	final Property p_propDef,
							final NumberValue p_value,
							final UnitLiteral p_expectedUnit )
	throws AssociatedPropertyException {
		if ( p_value == null ) {
			return null;
		}
		
		validateUnits( p_propDef, p_value, p_expectedUnit );
		
		if ( p_expectedUnit == null ) {
			return p_value.getScaledValue( p_value.getUnit() );
		}
		
		return p_value.getScaledValue( p_expectedUnit );
	}
	
	private Double value( 	final Property p_propDef,
							final RangeValue p_range,
							final UnitLiteral p_assocUnit ) 
	throws AssociatedPropertyException {
		final NumberValue minValue = p_range.getMinimumValue();
		final NumberValue maxValue = p_range.getMaximumValue();
		
		if ( minValue == null ) {
			return value( p_propDef, maxValue, p_assocUnit );
		}

		if ( maxValue == null ) {
			return value( p_propDef, minValue, p_assocUnit );
		}
		
		// Quick and dirty fix to override a problem with the unit of the minimal value with OSATE2
		// the minimal unit value may be undefined, see issue 110 : https://github.com/osate/osate2-core/issues/110
		// once the OSATE problem is fixed, this block should be removed !

// DB: Ticket #233 Now fixed in OSATE2
//		if (minValue.getUnit() == null && maxValue.getUnit() != null) {
//			// bypass checks from value(..) function and get the minimal value directly
//			return ( minValue.getScaledValue( p_assocUnit ) + value( p_propDef, maxValue, p_assocUnit ) ) / 2.0;
//		}
		
		return ( value( p_propDef, minValue, p_assocUnit ) + value( p_propDef, maxValue, p_assocUnit ) ) / 2.0;
	}
	
	private void validateUnits( final Property p_propDef,
								final NumberValue p_value,
								final UnitLiteral p_expectedUnit )
	throws AssociatedPropertyException {
		if ( p_value != null ) {
			if ( p_value.getUnit() == null && p_expectedUnit != null ) {
				throw new AssociatedPropertyException( getLanguageName() + " property " + p_propDef.getName() + " has no units while " + p_expectedUnit.getName() + " is expected." );
			}
	
			if ( p_value.getUnit() != null && p_expectedUnit == null ) {
				throw new AssociatedPropertyException( getLanguageName() + " property " + p_propDef.getName() + " has units of " + p_value.getUnit().getName() + " while no units is expceted." );
			}
		}
	}

	@Override
	public Object getDefaultPropertyValue( final Object p_property ) {
		final Property property = (Property) p_property;
		
		return property.getDefaultValue();
	}

	@Override
	public Property getPropertyFromValue( final Object p_value ) {
		if ( p_value instanceof PropertyExpression ) {
			return ( (PropertyAssociation) ( (PropertyExpression) p_value ).eContainer() ).getProperty();
		}
		
		return null;
	}
	
	@Override
	public PropertyValue getPropertyValue( 	final Object p_component,
											final Object p_property ) 
	throws AssociatedPropertyException {
		final NamedElement propHolder = (NamedElement) p_component;
		final Property propDef = (Property) p_property;
		final PropertySet propSet = (PropertySet) propDef.eContainer();
		
		try {
			final EList<PropertyExpression> values = propHolder.getPropertyValues( propSet.getName(), propDef.getName() );
			
			if ( values.size() > 1 ) {
				throw new AssociatedPropertyException( "Component " + getElementText( p_component ) + " contains several values for property " + getElementText( p_property ) );
			}
			
			if ( values.isEmpty() ) {
				return null;
			}
			
			final PropertyExpression propExpr = values.get( 0 );
			
			if ( !( propExpr instanceof PropertyValue ) ) {
				throw new AssociatedPropertyException( "Property value for property " + getElementText( p_property ) + " in component " + getElementText( p_component ) + " is an expression." );
			}
			
			return (PropertyValue) propExpr;
		}
		catch ( final PropertyDoesNotApplyToHolderException p_ex ) {
			throw new AssociatedPropertyException( getElementText( propDef ) + " does not apply to " + getElementText( p_component ) + "." );
		}
		catch( final InvalidModelException p_ex ) {
			final String resUri = propHolder.eResource() == null ? getElementText( propHolder ) : propHolder.eResource().getURI().toString();

			throw new AssociatedPropertyException( "Model " + resUri + " is invalid." );
		}
// DB: Property value for a component can be null.
//		catch( final UndefinedPropertyException p_ex ) {
//			throw new AssociatedPropertyException( p_ex.getLocalizedMessage() );
//		}
	}

	@Override
	public String getElementName( Object p_element ) {
		if ( p_element instanceof NamedElement ) {
			return ( (NamedElement) p_element ).getName();
		}
		
		return getElementText( p_element );
	}

	@Override
	public String getElementText( final Object p_element ) {
		if ( p_element instanceof Number ) {
			return NumberFormat.getNumberInstance( Locale.US ).format( p_element );
		}
		
		final StringBuilder text = new StringBuilder();
		
		 if ( p_element instanceof NamedElement ) {
			 if ( p_element instanceof ComponentInstance ) {
				 text.append( ( (ComponentInstance) p_element ).getCategory().getName() );
			 }
			 else {
				 text.append( ( (NamedElement) p_element ).eClass().getName() );
			 }

			 text.append( " " );
			 text.append( ( (NamedElement) p_element ).getName() );
		}
		
		return text.toString();
	}

	@Override
	public boolean isComponent( final Object p_object ) {
		return 	p_object instanceof ComponentType || p_object instanceof ComponentImplementation || 
				p_object instanceof ComponentInstance || p_object instanceof Subcomponent;
	}

	@Override
	public boolean isPropertyValue( final Object p_object ) {
		return true;//p_object instanceof PropertyValue;
	}

	@Override
	public EList<Object> componentTypes( Object p_component ) {
		final Set<Object> orderedTypesSet = new LinkedHashSet<Object>();
		
		if ( p_component instanceof Collection<?> ) {
			for ( final Object component : (Collection<?>) p_component ) {
				// DB: No need to add the type here. It will be added in the next call to componentTypes.
				orderedTypesSet.addAll( componentTypes( component ) );
			}
		}
		else {
			orderedTypesSet.add( p_component );
			
			if ( p_component instanceof ComponentClassifier ) {
				final Classifier extendedClassifier = ( (Classifier) p_component ).getExtended();
				
				if ( extendedClassifier != null ) {
					orderedTypesSet.addAll( componentTypes( extendedClassifier ) );
				}
				
				if ( p_component instanceof ComponentImplementation ) {
					orderedTypesSet.addAll( componentTypes( ( (ComponentImplementation) p_component ).getGenerals() ) );
				}
			}
			else if ( p_component instanceof Subcomponent ) {
				final Subcomponent subcompo = (Subcomponent) p_component;
				final ComponentImplementation impl = subcompo.getComponentImplementation();
				
				if ( impl !=  null ) {
					orderedTypesSet.addAll( componentTypes( impl ) );
				}
				else {
					orderedTypesSet.addAll( componentTypes( subcompo.getComponentType() ) );
				}
			}
			else if ( p_component instanceof ComponentInstance ) {
				orderedTypesSet.addAll( componentTypes( ( (ComponentInstance) p_component ).getSubcomponent() ) );
			}
		}
		
		return new BasicEList<Object>( orderedTypesSet );
	}
	
	protected boolean isModelResourceExtension( final String p_ext ) {
		return p_ext == null ? false : aadlExtensions.contains( p_ext );
	}
	
	@Override
	public boolean isModelResource( final Resource p_resource ) {
		return isModelResource( p_resource.getURI() );
	}

	protected boolean isModelResource( final URI p_resourceUri ) {
		return isModelResourceExtension( p_resourceUri.fileExtension() );
	}

	@Override
	public boolean isModelElement( final Object p_object ) {
		return p_object instanceof Element;
	}
	
	@Override
	public String getLanguageName() {
		return "AADL";
	}

	/* Ticked #239 Take into account the with clauses to define the scope of OCL queries.
	 * (non-Javadoc)
	 * @see fr.labsticc.framework.settings.model.settings.ScopeProvider#getScope(java.lang.Object)
	 */
	@Override
	public EList<Object> getScope( final Object p_context ) {
		if ( isModelElement( p_context ) ) {
			final EList<Object> roots = new BasicEList<Object>();
			final NamedElement rootElem = ( (Element) p_context ).getElementRoot();
			
			if ( rootElem instanceof InstanceObject ) {
				roots.addAll( rootElem.eResource().getContents() );
			}
			else {
				final ModelUnit modelUnit = (ModelUnit) rootElem;
				final Set<ModelUnit> dependencies = new HashSet<ModelUnit>();
				modelDependencies( modelUnit, dependencies );
				roots.addAll( dependencies );
			}

			return roots;
		}
		
		// Indicates that the context does not belong to this modeling language.
		return null;
	}
	
	public static void modelDependencies( 	final ModelUnit p_modelUnit,
											final Set<ModelUnit> p_modelUnits ) {
		p_modelUnits.add( p_modelUnit );
		
		if ( p_modelUnit instanceof AadlPackage ) {
			final AadlPackage aadlPackage = (AadlPackage) p_modelUnit;
			PackageSection section =  aadlPackage.getOwnedPrivateSection();
			
			if ( section != null ) {
				modelDependencies( section.getImportedUnits(), p_modelUnits );
			}
			
			section =  aadlPackage.getOwnedPublicSection();
			
			if ( section != null ) {
				modelDependencies( section.getImportedUnits(), p_modelUnits );
			}
		}
		else if ( p_modelUnit instanceof PropertySet ) {
			final PropertySet propSet = (PropertySet) p_modelUnit;
			modelDependencies( propSet.getImportedUnits(), p_modelUnits );
		}
	}
	
	private static void modelDependencies( 	final Collection<ModelUnit> p_imports,
											final Set<ModelUnit> p_modelUnits ) {
		for ( final ModelUnit modUnit : p_imports ) {
			
			// There may be circular dependencies between packages and property sets.
			if ( !p_modelUnits.contains( modUnit ) ) {
				modelDependencies( modUnit, p_modelUnits );
			}
		}
	}

	@Override
	public Object getElementFromScope( final Object p_element ) {
		final ResourceSet resSet = getCustomResourcet();

		if ( resSet != null && isModelElement( p_element ) ) {
			return resSet.getEObject( EcoreUtil.getURI( (EObject) p_element ), true );
		}
		
		return null;
	}
}