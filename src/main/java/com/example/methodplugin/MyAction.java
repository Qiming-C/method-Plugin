package com.example.methodplugin;

import java.util.Arrays;
import java.util.List;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;

public class MyAction
    extends AnAction
{
  @Override
  public void actionPerformed(AnActionEvent e) {
      Project project = e.getProject();
      Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
      List<String> vulnerableMethods = Arrays.asList("doSomethingVulnerable");

      PsiFile file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
    VulnerableCallVisitor visitor = new VulnerableCallVisitor(vulnerableMethods);
      file.accept(visitor);
  }
}
