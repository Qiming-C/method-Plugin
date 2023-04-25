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
import org.jetbrains.annotations.NotNull;

public class JavaVulnerableMethodCallAnnotator extends VulnerableMethodCallAnnotator
{

  private final List<String> vulnerableMethods = List.of("doSomethingVulnerable");

  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {
    if (element instanceof PsiMethodCallExpression) {
      PsiMethodCallExpression methodCallExpression = (PsiMethodCallExpression) element;
      TextRange range = methodCallExpression.getTextRange();
      Integer severity = 1;
      PsiMethod psiMethod = methodCallExpression.resolveMethod();
      if (psiMethod != null && vulnerableMethods.contains(psiMethod.getName())) {
        holder.newAnnotation(HighlightSeverity.ERROR, "")
            .tooltip("This is a tooltip")
            .range(range)
            .enforcedTextAttributes(VulnerableMethodCallAnnotator.HighLightError)
            .create();
      }
    }
  }
}
