package com.example.methodplugin;

import com.intellij.psi.PsiMethodCallExpression;

public interface MethodHighlighter
{

  public void highlightMethodCall(PsiMethodCallExpression expression, Integer severity);
}
