package com.example.BankingApp.util;
import java.util.HashMap;
import java.util.Map;

public class BijoyToUnicodeConverter {
    private static final Map<String, String> bijoyToUnicodeMap = new HashMap<>();

    static {
        // Vowels
        bijoyToUnicodeMap.put("Av", "আ");
        bijoyToUnicodeMap.put("A", "অ");
        bijoyToUnicodeMap.put("B", "ই");
        bijoyToUnicodeMap.put("C", "ঈ");
        bijoyToUnicodeMap.put("D", "উ");
        bijoyToUnicodeMap.put("E", "ঊ");
        bijoyToUnicodeMap.put("F", "ঋ");

        // Consonants
        bijoyToUnicodeMap.put("G", "ক");
        bijoyToUnicodeMap.put("H", "খ");
        bijoyToUnicodeMap.put("I", "গ");
        bijoyToUnicodeMap.put("J", "ঘ");
        bijoyToUnicodeMap.put("K", "ঙ");
        bijoyToUnicodeMap.put("L", "চ");
        bijoyToUnicodeMap.put("M", "ছ");
        bijoyToUnicodeMap.put("N", "জ");
        bijoyToUnicodeMap.put("O", "ঝ");
        bijoyToUnicodeMap.put("P", "ঞ");
        bijoyToUnicodeMap.put("Q", "ট");
        bijoyToUnicodeMap.put("R", "ঠ");
        bijoyToUnicodeMap.put("S", "ড");
        bijoyToUnicodeMap.put("T", "ঢ");
        bijoyToUnicodeMap.put("U", "ণ");
        bijoyToUnicodeMap.put("V", "ত");
        bijoyToUnicodeMap.put("W", "থ");
        bijoyToUnicodeMap.put("X", "দ");
        bijoyToUnicodeMap.put("Y", "ধ");
        bijoyToUnicodeMap.put("Z", "ন");

        bijoyToUnicodeMap.put("_", "ব");
        bijoyToUnicodeMap.put("`", "ভ");
        bijoyToUnicodeMap.put("a", "ম");
        bijoyToUnicodeMap.put("b", "য");
        bijoyToUnicodeMap.put("c", "র");
        bijoyToUnicodeMap.put("d", "ল");
        bijoyToUnicodeMap.put("e", "শ");
        bijoyToUnicodeMap.put("f", "ষ");
        bijoyToUnicodeMap.put("g", "স");
        bijoyToUnicodeMap.put("h", "হ");
        bijoyToUnicodeMap.put("i", "ড়");
        bijoyToUnicodeMap.put("j", "ঢ়");
        bijoyToUnicodeMap.put("k", "য়");
        bijoyToUnicodeMap.put("l", "ৎ");
        bijoyToUnicodeMap.put("m", "ং");
        bijoyToUnicodeMap.put("n", "ঃ");
        bijoyToUnicodeMap.put("o", "ঁ");

        // Vowel Signs (Kar)
        bijoyToUnicodeMap.put("q", "া");
        bijoyToUnicodeMap.put("r", "ি");
        bijoyToUnicodeMap.put("s", "ী");
        bijoyToUnicodeMap.put("t", "ু");
        bijoyToUnicodeMap.put("u", "ূ");
        bijoyToUnicodeMap.put("v", "ৃ");
        bijoyToUnicodeMap.put("w", "ে");
        bijoyToUnicodeMap.put("x", "ৈ");
        bijoyToUnicodeMap.put("y", "ো");
        bijoyToUnicodeMap.put("z", "ৌ");

        // Numbers
        bijoyToUnicodeMap.put("0", "০");
        bijoyToUnicodeMap.put("1", "১");
        bijoyToUnicodeMap.put("2", "২");
        bijoyToUnicodeMap.put("3", "৩");
        bijoyToUnicodeMap.put("4", "৪");
        bijoyToUnicodeMap.put("5", "৫");
        bijoyToUnicodeMap.put("6", "৬");
        bijoyToUnicodeMap.put("7", "৭");
        bijoyToUnicodeMap.put("8", "৮");
        bijoyToUnicodeMap.put("9", "৯");

        // Special Characters and Punctuation
        bijoyToUnicodeMap.put("`", "্");   // Halant
        bijoyToUnicodeMap.put("^", "ঁ");   // Chandrabindu
        bijoyToUnicodeMap.put("&", "ং");   // Anusvara
        bijoyToUnicodeMap.put("*", "ঃ");   // Visarga
    }


    public static String convert(String asciiText) {
        if (asciiText == null || asciiText.isEmpty()) {
            return asciiText;
        }
        StringBuilder unicodeText = new StringBuilder();
        for (int i = 0; i < asciiText.length(); i++) {
            String character = String.valueOf(asciiText.charAt(i));
            unicodeText.append(bijoyToUnicodeMap.getOrDefault(character, character));
        }
        return unicodeText.toString();
    }

    public static boolean isBijoyAscii(String text) {
        return text != null && text.matches("[\u0020-\u007E]+");
    }
}
