package com.example.autogenspring

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors


fun main(args: Array<String>) {
    val a = Files.walk(File("./").toPath()).collect(Collectors.toList())
    println(a)
}