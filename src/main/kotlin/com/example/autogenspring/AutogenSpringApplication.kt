package com.example.autogenspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class AutogenSpringApplication

fun main(args: Array<String>) {
    println("请输入maven的groupId：")
    val sc = Scanner(System.`in`)
    projectGroupId = sc.next()
    println("请输入maven的ArtifactId：")
    projectArtifactId = sc.next()
    println("请输入maven的版本号(默认为1.0.0.SNAPSHOT<输入N>):")
    projectVersion = sc.next()
    if (projectVersion == "N") projectVersion = "1.0.0.SNAPSHOT"
    println("请输入springboot的版本号(默认为2.0.1.RELEASE<输入N>):")
    springbootVersion = sc.next()
    if (springbootVersion == "N") springbootVersion = "2.0.1.RELEASE"
    println("请输入数据库的地址(默认为127.0.0.1<输入N>):")
    hostUrl = sc.next()
    if (hostUrl == "N") hostUrl = "127.0.0.1"
    println("请输入数据库的端口号(默认为3306<输入N>):")
    mySQLPort = sc.next()
    if (mySQLPort == "N") mySQLPort = "3306"
    println("请输入数据库的名称(默认test<输入N>):")
    databaseName = sc.next()
    if (databaseName == "N") databaseName = "test"
    println("请输入数据库的用户名(默认为root<输入N>):")
    userName = sc.next()
    if (userName == "N") userName = "root"
    println("请输入数据库的密码(默认为无<输入N>):")
    password = sc.next()
    if (password == "N") password = ""
//    projectGroupId = "com.zmy"
//
//    projectArtifactId = "cosmetic"
//
//    projectVersion = "1.0.0.SNAPSHOT"
//
//    springbootVersion = "2.0.1.RELEASE"
//
//    databaseName = "test"
//    userName = "root"
//    password = ""
//    mySQLPort = "3306"
//    hostUrl = "127.0.0.1"
    // 参数有 groupId architId projectVersion springbootVersion
    // 数据库地址， database， 用户名 密码， 端口
    createStructure()
    connect()
    println("项目生成完成")
}
