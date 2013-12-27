package fr.labsticc.framework.constraints.ide.view;

import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.view.IContextualConstraintPanelActionHandler;

public interface IConstraintExpressionPanelActionHandler extends IContextualConstraintPanelActionHandler {

	Object evaluateExpression();

	FormalLanguageExpression getExpression();

	void setExpression( FormalLanguageExpression p_expression );
}
