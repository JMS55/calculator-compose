object Interpreter {
    private var number_regex = Regex("[0-9]+(\\.[0-9]+)?")

    fun calculate(equation: String): Double? {
        return tokenize(equation)?.let { tokens -> parse(tokens)?.let { equation -> evaluate(equation) } }
    }

    private fun tokenize(equation: String): List<Token>? {
        // Error on blank equation
        if (equation.isEmpty()) {
            return null
        }

        val tokens = mutableListOf<Token>()

        var startIndex = 0
        while (startIndex < equation.length) {
            // Check if number
            val match = number_regex.find(equation, startIndex)
            if (match != null && match.range.first == startIndex) {
                tokens.add(Token.Number(match.value.toDouble()))
                startIndex = match.range.last + 1
                continue
            }

            // Check if operator
            when (val operator = equation[startIndex]) {
                '+', '-', '×', '÷' -> {
                    tokens.add(charToToken(operator))
                    startIndex += 1
                    continue
                }
            }

            // Else error
            return null
        }

        return tokens
    }

    private fun parse(tokens: List<Token>): Equation? {
        var tokenIndex = 0
        val a = parseA(tokens, tokenIndex) ?: return null
        tokenIndex = a.second
        if (tokenIndex != tokens.size) {
            return null
        }
        return a.first
    }

    private fun parseA(tokens: List<Token>, i: Int): Pair<Equation, Int>? {
        var tokenIndex = i
        val m = parseM(tokens, tokenIndex) ?: return null
        tokenIndex = m.second
        return when (tokens.getOrNull(tokenIndex)) {
            Token.Add -> {
                tokenIndex += 1
                val a = parseA(tokens, tokenIndex) ?: return null
                tokenIndex = a.second
                Pair(Equation.Add(m.first, a.first), tokenIndex)
            }
            Token.Subtract -> {
                tokenIndex += 1
                val a = parseA(tokens, tokenIndex) ?: return null
                tokenIndex = a.second
                Pair(Equation.Subtract(m.first, a.first), tokenIndex)
            }
            else -> Pair(m.first, tokenIndex)
        }
    }

    private fun parseM(tokens: List<Token>, i: Int): Pair<Equation, Int>? {
        var tokenIndex = i
        val v = parseN(tokens, tokenIndex) ?: return null
        tokenIndex = v.second
        return when (tokens.getOrNull(tokenIndex)) {
            Token.Multiply -> {
                tokenIndex += 1
                val m = parseM(tokens, tokenIndex) ?: return null
                tokenIndex = m.second
                Pair(Equation.Multiply(v.first, m.first), tokenIndex)
            }
            Token.Divide -> {
                tokenIndex += 1
                val m = parseM(tokens, tokenIndex) ?: return null
                tokenIndex = m.second
                Pair(Equation.Divide(v.first, m.first), tokenIndex)
            }
            else -> Pair(v.first, tokenIndex)
        }
    }

    private fun parseN(tokens: List<Token>, tokenIndex: Int): Pair<Equation, Int>? {
        return when (val tok = tokens.getOrNull(tokenIndex)) {
            is Token.Number -> Pair(Equation.Number(tok.value), tokenIndex + 1)
            else -> null
        }
    }

    private fun evaluate(equation: Equation): Double {
        return when (equation) {
            is Equation.Number -> equation.value
            is Equation.Add -> evaluate(equation.left) + evaluate(equation.right)
            is Equation.Subtract -> evaluate(equation.left) - evaluate(equation.right)
            is Equation.Multiply -> evaluate(equation.left) * evaluate(equation.right)
            is Equation.Divide -> evaluate(equation.left) / evaluate(equation.right)
        }
    }
}

private sealed class Token {
    data class Number(val value: Double) : Token()
    object Add : Token()
    object Subtract : Token()
    object Multiply : Token()
    object Divide : Token()
}

private fun charToToken(char: Char): Token {
    return when (char) {
        '+' -> Token.Add
        '-' -> Token.Subtract
        '×' -> Token.Multiply
        '÷' -> Token.Divide
        else -> throw Exception("Unreachable")
    }
}

private sealed class Equation {
    data class Number(val value: Double) : Equation()
    data class Add(val left: Equation, val right: Equation) : Equation()
    data class Subtract(val left: Equation, val right: Equation) : Equation()
    data class Multiply(val left: Equation, val right: Equation) : Equation()
    data class Divide(val left: Equation, val right: Equation) : Equation()
}
