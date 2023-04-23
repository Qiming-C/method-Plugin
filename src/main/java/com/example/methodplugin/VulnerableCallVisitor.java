package com.example.methodplugin;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.psi.KtCallExpression;

import java.util.List;

public class VulnerableCallVisitor extends PsiRecursiveElementVisitor
{
  private List<String> vulnerableMethods;

  public VulnerableCallVisitor(List<String> vulnerableMethods) {
    this.vulnerableMethods = vulnerableMethods;
  }


  @Override
  public void visitElement(@NotNull final PsiElement element) {
    System.out.println("visiting element");
    if (element instanceof PsiMethodCallExpression) {
      PsiMethodCallExpression methodCall = (PsiMethodCallExpression) element;

      // Check if the method is vulnerable
      if (vulnerableMethods.contains(methodCall.resolveMethod().getName())) {
        // Highlight the method call
        System.out.println("found the method call");
        ;
      }
    }
    // Visit the element's children
    super.visitElement(element);
  }
}
}
