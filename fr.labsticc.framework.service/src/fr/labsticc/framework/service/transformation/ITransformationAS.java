package fr.labsticc.framework.service.transformation;

import java.util.List;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.transformation.IModel;
import fr.labsticc.framework.core.transformation.ITransformation;
import fr.labsticc.framework.core.transformation.TransformationException;
import fr.labsticc.framework.service.IService;

/**
 * This interface defines an API to execute abstract model transformations. No specific model transformation and meta-model
 * framework is assumed.
 *
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public interface ITransformationAS extends IService {

	/**
	 * Transforms a list of input models into a list of output models as specified by a given model transformation.
	 * @param p_inModels A <code>List</code> of <code>IModel</code> input models that must match the model transformation
	 * specification (numbers of models and their meta_models).
	 * @param p_transformation The model transformation specification.
	 * @return A <code>List</code> of <code>IModel</code> output models that must match the model transformation
	 * specification (numbers of models and their meta_models).
	 * @throws ResourceAccessException
	 * @throws TransformationException
	 */
	List<IModel> transform( List<IModel> p_inModels,
							ITransformation p_transformation )
	throws ResourceAccessException, TransformationException;

	/**
	 * Performs the reverse of the specified model transformation (creates the input models as specified by the transformation
	 * from the output models as specified by the transformation).
	 * @param p_outModels The output models as specified by the transformation.
	 * @param p_transformation The model transformation specification.
	 * @return A <code>List</code> of <code>IModel</code> models that must match the input model transformation
	 * specification (numbers of models and their meta_models). 
	 * @throws ResourceAccessException
	 * @throws TransformationException
	 */
	List<IModel> reverseTransform( 	List<IModel> p_outModels,
									ITransformation p_transformation )
	throws ResourceAccessException, TransformationException;
}
