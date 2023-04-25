package com.example.methodplugin;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.List;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

public abstract class VulnerableMethodCallAnnotator implements Annotator
{
  public static final TextAttributes HighLightError = new TextAttributes(
      DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR.getDefaultAttributes().getForegroundColor(),
      DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR.getDefaultAttributes().getBackgroundColor(),
      JBColor.RED,
      EffectType.LINE_UNDERSCORE,
      Font.PLAIN);

  private static final TextAttributes HighLightWarning = new TextAttributes(
      DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR.getDefaultAttributes().getForegroundColor(),
      DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR.getDefaultAttributes().getBackgroundColor(),
      JBColor.YELLOW,
      EffectType.LINE_UNDERSCORE,
      Font.PLAIN);

  private static final TextAttributes HighLightInfo = new TextAttributes(
      DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR.getDefaultAttributes().getForegroundColor(),
      DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR.getDefaultAttributes().getBackgroundColor(),
      JBColor.BLUE,
      EffectType.LINE_UNDERSCORE,
      Font.PLAIN);

}
