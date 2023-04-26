package com.example.methodplugin;

import java.util.List;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiSubstitutor;
import org.jetbrains.annotations.NotNull;

public class JavaVulnerableMethodCallAnnotator extends VulnerableMethodCallAnnotator
{

  private final List<String> vulnerableMethods = List.of("doSomethingVulnerable");

  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {
    if (element instanceof PsiMethodCallExpression) {
      PsiMethodCallExpression methodCallExpression = (PsiMethodCallExpression) element;
      TextRange range = methodCallExpression.getTextRange();
      PsiMethod psiMethod = methodCallExpression.resolveMethod();
        if (psiMethod != null && vulnerableMethods.contains(psiMethod.getName())) {
          StringBuilder tooltip = new StringBuilder();
          String title = "<h1>Vulnerable Method Call</h1><br/>";
          String componentName = psiMethod.getContainingClass().getQualifiedName();
          String methodName = psiMethod.getName();

          String remediation = "<p>Remediation: Avoid using this method</p>";
          tooltip.append(title);
          tooltip.append("<b>Component: </b>").append(componentName).append("<br/>");
          tooltip.append("<b>Method: </b>").append(methodName).append("<br/>");
          tooltip.append(remediation);
          holder.newAnnotation(HighlightSeverity.ERROR, "")
              .tooltip(tooltip.toString())
              .range(range)
              .enforcedTextAttributes(VulnerableMethodCallAnnotator.HighLightError)
              .create();
        }

    }
  }
}
