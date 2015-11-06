package org.eclipse.fx.code.editor.ldef.langs.fx.swift;

public class Swift__dftl_partition_content_type extends org.eclipse.jface.text.rules.RuleBasedScanner {
	public Swift__dftl_partition_content_type() {
		org.eclipse.jface.text.rules.Token swift_defaultToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.fx.text.ui.TextAttribute("swift.swift_default"));
		setDefaultReturnToken(swift_defaultToken);
		org.eclipse.jface.text.rules.Token swift_keywordToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.fx.text.ui.TextAttribute("swift.swift_keyword"));
		org.eclipse.jface.text.rules.Token swift_builtin_typeToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.fx.text.ui.TextAttribute("swift.swift_builtin_type"));
		org.eclipse.jface.text.rules.Token swift_operatorToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.fx.text.ui.TextAttribute("swift.swift_operator"));
		org.eclipse.jface.text.rules.Token swift_numberToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.fx.text.ui.TextAttribute("swift.swift_number"));
		org.eclipse.jface.text.rules.Token swift_attributeToken = new org.eclipse.jface.text.rules.Token(new org.eclipse.fx.text.ui.TextAttribute("swift.swift_attribute"));
		org.eclipse.jface.text.rules.IRule[] rules = new org.eclipse.jface.text.rules.IRule[4];
		rules[0] = new org.eclipse.fx.text.rules.CharacterRule(swift_operatorToken, new char[] {'~','!','%','^','&','*','-','+','=','|','/','.','<','>','?'});
		rules[1] = new org.eclipse.fx.text.rules.RegexRule(swift_numberToken, java.util.regex.Pattern.compile("[0-9]"),1,java.util.regex.Pattern.compile("[0-9|\\.|e|E|-]"));
		rules[2] = new org.eclipse.fx.text.rules.RegexRule(swift_attributeToken, java.util.regex.Pattern.compile("@"),1,java.util.regex.Pattern.compile("[\\w]"));

		org.eclipse.fx.text.rules.JavaLikeWordDetector wordDetector= new org.eclipse.fx.text.rules.JavaLikeWordDetector();
		org.eclipse.fx.text.rules.CombinedWordRule combinedWordRule= new org.eclipse.fx.text.rules.CombinedWordRule(wordDetector, swift_defaultToken);
		{
			org.eclipse.fx.text.rules.CombinedWordRule.WordMatcher swift_keywordWordRule = new org.eclipse.fx.text.rules.CombinedWordRule.WordMatcher();
			swift_keywordWordRule.addWord("as", swift_keywordToken);
			swift_keywordWordRule.addWord("break", swift_keywordToken);
			swift_keywordWordRule.addWord("case", swift_keywordToken);
			swift_keywordWordRule.addWord("catch", swift_keywordToken);
			swift_keywordWordRule.addWord("class", swift_keywordToken);
			swift_keywordWordRule.addWord("continue", swift_keywordToken);
			swift_keywordWordRule.addWord("convenience", swift_keywordToken);
			swift_keywordWordRule.addWord("default", swift_keywordToken);
			swift_keywordWordRule.addWord("defer", swift_keywordToken);
			swift_keywordWordRule.addWord("deinit", swift_keywordToken);
			swift_keywordWordRule.addWord("didSet", swift_keywordToken);
			swift_keywordWordRule.addWord("do", swift_keywordToken);
			swift_keywordWordRule.addWord("dynamic", swift_keywordToken);
			swift_keywordWordRule.addWord("else", swift_keywordToken);
			swift_keywordWordRule.addWord("extension", swift_keywordToken);
			swift_keywordWordRule.addWord("fallthrough", swift_keywordToken);
			swift_keywordWordRule.addWord("final", swift_keywordToken);
			swift_keywordWordRule.addWord("for", swift_keywordToken);
			swift_keywordWordRule.addWord("func", swift_keywordToken);
			swift_keywordWordRule.addWord("get", swift_keywordToken);
			swift_keywordWordRule.addWord("guard", swift_keywordToken);
			swift_keywordWordRule.addWord("if", swift_keywordToken);
			swift_keywordWordRule.addWord("import", swift_keywordToken);
			swift_keywordWordRule.addWord("in", swift_keywordToken);
			swift_keywordWordRule.addWord("infix", swift_keywordToken);
			swift_keywordWordRule.addWord("init", swift_keywordToken);
			swift_keywordWordRule.addWord("inout", swift_keywordToken);
			swift_keywordWordRule.addWord("internal", swift_keywordToken);
			swift_keywordWordRule.addWord("is", swift_keywordToken);
			swift_keywordWordRule.addWord("lazy", swift_keywordToken);
			swift_keywordWordRule.addWord("let", swift_keywordToken);
			swift_keywordWordRule.addWord("mutating", swift_keywordToken);
			swift_keywordWordRule.addWord("nil", swift_keywordToken);
			swift_keywordWordRule.addWord("operator", swift_keywordToken);
			swift_keywordWordRule.addWord("optional", swift_keywordToken);
			swift_keywordWordRule.addWord("override", swift_keywordToken);
			swift_keywordWordRule.addWord("postfix", swift_keywordToken);
			swift_keywordWordRule.addWord("prefix", swift_keywordToken);
			swift_keywordWordRule.addWord("private", swift_keywordToken);
			swift_keywordWordRule.addWord("protocol", swift_keywordToken);
			swift_keywordWordRule.addWord("public", swift_keywordToken);
			swift_keywordWordRule.addWord("repeat", swift_keywordToken);
			swift_keywordWordRule.addWord("required", swift_keywordToken);
			swift_keywordWordRule.addWord("return", swift_keywordToken);
			swift_keywordWordRule.addWord("self", swift_keywordToken);
			swift_keywordWordRule.addWord("set", swift_keywordToken);
			swift_keywordWordRule.addWord("static", swift_keywordToken);
			swift_keywordWordRule.addWord("subscript", swift_keywordToken);
			swift_keywordWordRule.addWord("super", swift_keywordToken);
			swift_keywordWordRule.addWord("switch", swift_keywordToken);
			swift_keywordWordRule.addWord("throws", swift_keywordToken);
			swift_keywordWordRule.addWord("try", swift_keywordToken);
			swift_keywordWordRule.addWord("typealias", swift_keywordToken);
			swift_keywordWordRule.addWord("unowned", swift_keywordToken);
			swift_keywordWordRule.addWord("var", swift_keywordToken);
			swift_keywordWordRule.addWord("weak", swift_keywordToken);
			swift_keywordWordRule.addWord("where", swift_keywordToken);
			swift_keywordWordRule.addWord("while", swift_keywordToken);
			swift_keywordWordRule.addWord("willSet", swift_keywordToken);
			swift_keywordWordRule.addWord("struct", swift_keywordToken);
			swift_keywordWordRule.addWord("enum", swift_keywordToken);
			combinedWordRule.addWordMatcher(swift_keywordWordRule);
		}
		{
			org.eclipse.fx.text.rules.CombinedWordRule.WordMatcher swift_builtin_typeWordRule = new org.eclipse.fx.text.rules.CombinedWordRule.WordMatcher();
			swift_builtin_typeWordRule.addWord("Int", swift_builtin_typeToken);
			swift_builtin_typeWordRule.addWord("Bool", swift_builtin_typeToken);
			combinedWordRule.addWordMatcher(swift_builtin_typeWordRule);
		}
		rules[3] = combinedWordRule;
		setRules(rules);
	}
}
