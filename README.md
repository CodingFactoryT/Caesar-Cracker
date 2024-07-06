# Introduction
The Caesar-Cracker is a program we developed in a computer science lesson at school. It is able to crack any message that was decrypted with the <a href="https://en.wikipedia.org/wiki/Caesar_cipher">Caesar-cypher</a>.

# How it works
The Caesar-Cracker will generate 26 versions of the encrypted message (each version is decrypted by shifting the letters by n positions in one direction).
After that, it compares each word of each version with an English dictionary. If any decrypted word is a valid English word, the whole version is considered to possibly be the correct decryption and is outputted to the console. <br>

It also allows you to encrypt a message to get an example.
