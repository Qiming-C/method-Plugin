package com.example.methodplugin;

import java.awt.*;

import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.ui.JBColor;


public class MethodHighlighterImpl
    implements MethodHighlighter
{
  private static final TextAttributes HighLightError = new TextAttributes(
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
  @Override
  public static void highlightMethodCall(final PsiMethodCallExpression expression, final Integer severity) {
    TextRange range = expression.getTextRange();
    Editor editor = FileEditorManager.getInstance(expression.getProject()).getSelectedTextEditor();
    if (editor != null) {
      TextAttributes textAttributes = checkMethodSeverityColor(severity);
        if (textAttributes != null) {
          editor.getMarkupModel().addRangeHighlighter(range.getStartOffset(), range.getEndOffset(), HighlighterLayer.ADDITIONAL_SYNTAX, textAttributes, HighlighterTargetArea.EXACT_RANGE);
        }
    }

  }

  private TextAttributes checkMethodSeverityColor(final int severity) {

    if (severity >= 7) {
      return HighLightError;
    }
    else if (severity >= 3) {
      return HighLightWarning;
    }
    else if (severity >= 0) {
      return HighLightInfo;
    }
    return null;
  }
}
