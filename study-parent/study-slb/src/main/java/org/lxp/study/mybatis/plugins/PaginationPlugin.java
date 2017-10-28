package org.lxp.study.mybatis.plugins;

import static org.mybatis.generator.api.dom.java.JavaVisibility.PROTECTED;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class PaginationPlugin extends PluginAdapter {
  private final String pageClass = "org.lxp.study.util.Page";

  @Override
  public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    addPage(topLevelClass, introspectedTable, "page");
    return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
  }

  @Override
  public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
      IntrospectedTable introspectedTable) {
    XmlElement page = new XmlElement("if");
    page.addAttribute(new Attribute("test", "page != null"));
    page.addElement(new TextElement("limit #{page.offset} , #{page.pageSize}"));
    element.addElement(page);
    return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
  }

  public boolean validate(List<String> arg0) {
    return true;
  }

  private void addPage(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
    topLevelClass.addImportedType(new FullyQualifiedJavaType(pageClass));
    /**
     * http://blog.csdn.net/luoyongsir/article/details/16946153
     */
    String clazz = String.format("%s<%s>", pageClass, introspectedTable.getTableConfiguration().getDomainObjectName());
    CommentGenerator commentGenerator = context.getCommentGenerator();
    Field field = new Field();
    field.setVisibility(PROTECTED);
    field.setType(new FullyQualifiedJavaType(clazz));
    field.setName(name);
    commentGenerator.addFieldComment(field, introspectedTable);
    topLevelClass.addField(field);
    char c = name.charAt(0);
    String camel = Character.toUpperCase(c) + name.substring(1);
    Method method = new Method();
    method.setVisibility(PUBLIC);
    method.setName("set" + camel);
    method.addParameter(new Parameter(new FullyQualifiedJavaType(clazz), name));
    method.addBodyLine("this." + name + "=" + name + ";");
    commentGenerator.addGeneralMethodComment(method, introspectedTable);
    topLevelClass.addMethod(method);
    method = new Method();
    method.setVisibility(PUBLIC);
    method.setReturnType(new FullyQualifiedJavaType(clazz));
    method.setName("get" + camel);
    method.addBodyLine("return " + name + ";");
    commentGenerator.addGeneralMethodComment(method, introspectedTable);
    topLevelClass.addMethod(method);
  }

}
