public interface Converter {
    String convert(ArithmeticExpression expression);

    String nextToken(String s, int start);

    boolean isOperand(String s);

     class TokenBuilder {
        private StringBuilder tokenBuilder = new StringBuilder();

        public void append(char c) {
            tokenBuilder.append(c);
        }

        public String build() {
            return tokenBuilder.toString();
        }
    }
}
