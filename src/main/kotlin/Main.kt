fun vigenereCipher(str: String, key: String, status: String): String {
    val lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray()
    val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    val space = "".toCharArray()
    val n = when {
        str.all { it.isLowerCase() } -> lowercase
        str.all { it.isUpperCase() } -> uppercase
        else -> space
    }
    var result = ""
    var keyIndex = 0
    for (char in str) {
        val p = n.indexOf(char)
        val d = alphabetd.indexOf(char)
        if (p == -1) {
            result += char
            keyIndex --
            continue
        }
        val keyChar = key[keyIndex % key.length]
        if (status == "encrypt") {
            val k = n.indexOf(keyChar)
            val c = (p + k) % n.size
            result += n[c]
        }
        if (status == "decrypt") {
            val k = alphabetd.indexOf(keyChar)
            val c = (d - k + alphabetd.size) % alphabetd.size
            result += alphabetd[c]
        }
        keyIndex++
    }
    return result
}

private var alphabetd : CharArray = charArrayOf()

fun alphapetOfDecrypt(option : String) {
    var alphabet: CharArray = charArrayOf()
    when (option) {
        1.toString() -> alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()
        2.toString() -> alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        else -> println("Invalid option.")
    }
    alphabetd = alphabet
}

fun main() {
    println("Enter the text:")
    var inputText = readlnOrNull()?.takeWhile { it.isLetter() || it.isWhitespace() } ?: ""
    while (inputText.isBlank()) {
        println("Invalid input. Please enter valid text.")
        println("Enter the text:")
        inputText = readlnOrNull()?.takeWhile { it.isLetter() || it.isWhitespace() } ?: ""
    }
    println("Enter the key:")
    val key = readlnOrNull()?.takeWhile { it.isLetter() } ?: ""
    if (key.isBlank()) {
        println("Invalid input. Key must contain at least one letter.")
        return
    }
    println("Choose operation (encrypt/decrypt):")
    val operation = readlnOrNull()?.lowercase() ?: ""
    if (operation == "decrypt") {
        println("Choose Number of the Following:")
        println("1) LowerCase ")
        println("2) UpperCase ")
        val option = readlnOrNull()
        alphapetOfDecrypt(option.toString())
    }
    if (operation != "encrypt" && operation != "decrypt") {
        println("Invalid operation. Please choose either 'encrypt' or 'decrypt'.")
        return
    }
    val result = vigenereCipher(inputText, key, operation)
    println("Result: $result")
}