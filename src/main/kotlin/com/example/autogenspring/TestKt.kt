package com.example.autogenspring

import java.io.File
import java.sql.DriverManager
import java.sql.ResultSetMetaData
import java.util.*

/**
 * @Author: my.zhou
 *
 * @Date: 2018/4/25
 *
 * @Desc: com.zmy.autogen
 */

var projectGroupId = "com.zmy"

var projectArtifactId = "test"

var projectVersion = "1.0.0.SNAPSHOT"

var springbootVersion = "2.0.1.RELEASE"

var databaseName = "test"
var userName = "root"
var password = ""
var mySQLPort = "3306"
var hostUrl = "127.0.0.1"


fun dirIsExist(f: File) {
    if (!f.exists()) {
        f.mkdirs()
    }
}

fun fileIsExist(f: File) {
    if (!f.exists()) {
        f.createNewFile()
    }
}

fun createStructure() {
    val mf = File(projectArtifactId)
    var mp = File("${projectArtifactId}/pom.xml")
    val pp = projectGroupId.split(".").joinToString("/")
    val domaind = File("${projectArtifactId}/${projectArtifactId}-domain")
    val domainsrc = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain")
    val domainsrcpo = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/po")
    val domainsrcform = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/form")
    val domainsrcbo = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/bo")
    val domainsrcvo = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/vo")
    val domainf = File("${projectArtifactId}/${projectArtifactId}-domain/pom.xml")

    val daod = File("${projectArtifactId}/${projectArtifactId}-dao")
    val daosrc = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/java/${pp}/${projectArtifactId}/dao")
    val daof = File("${projectArtifactId}/${projectArtifactId}-dao/pom.xml")
    val daor = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/resources/${pp}/${projectArtifactId}/dao")

    val serviced = File("${projectArtifactId}/${projectArtifactId}-service")
    val servicesrc = File("${projectArtifactId}/${projectArtifactId}-service/src/main/java/${pp}/${projectArtifactId}/service")
    val servicef = File("${projectArtifactId}/${projectArtifactId}-service/pom.xml")

    val webd = File("${projectArtifactId}/${projectArtifactId}-web")
    val websrc = File("${projectArtifactId}/${projectArtifactId}-web/src/main/java/${pp}/${projectArtifactId}/web")
    val webf = File("${projectArtifactId}/${projectArtifactId}-web/pom.xml")
    val webr = File("${projectArtifactId}/${projectArtifactId}-web/src/main/resources")
    val weba = File("${projectArtifactId}/${projectArtifactId}-web/src/main/resources/application.properties")
    var classname = projectArtifactId[0].toUpperCase().toString()
    for (i in 1..(projectArtifactId.length - 1)) {
        classname += projectArtifactId[i]
    }
    val webmain = File("${projectArtifactId}/${projectArtifactId}-web/src/main/java/${pp}/${projectArtifactId}/web/${classname}WebApplication.java")

    dirIsExist(mf)
    dirIsExist(domaind)
    dirIsExist(daod)
    dirIsExist(serviced)
    dirIsExist(webd)
    dirIsExist(domainsrc)
    dirIsExist(domainsrcpo)
    dirIsExist(domainsrcform)
    dirIsExist(domainsrcbo)
    dirIsExist(domainsrcvo)
    dirIsExist(daosrc)
    dirIsExist(daor)
    dirIsExist(servicesrc)
    dirIsExist(websrc)
    dirIsExist(webr)
    fileIsExist(mp)
    fileIsExist(domainf)
    fileIsExist(daof)
    fileIsExist(servicef)
    fileIsExist(webf)
    fileIsExist(weba)
    fileIsExist(webmain)
    generatePom()
    generateDomain()
    generateDao()
    generateService()
    generateWeb()
    generateMain()
    generateProperties()
}


fun generatePom() {
    println("最外层pom.xml <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    val s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
            "    <modelVersion>4.0.0</modelVersion>\n" +
            "\n" +
            "    <groupId>${projectGroupId}</groupId>\n" +
            "    <artifactId>${projectArtifactId}</artifactId>\n" +
            "    <version>${projectVersion}</version>\n" +
            "    <packaging>pom</packaging>\n" +
            "\n" +
            "    <parent>\n" +
            "        <groupId>org.springframework.boot</groupId>\n" +
            "        <artifactId>spring-boot-starter-parent</artifactId>\n" +
            "        <version>${springbootVersion}</version>\n" +
            "        <relativePath/> <!-- lookup parent from repository -->\n" +
            "    </parent>\n" +
            "\n" +
            "    <properties>\n" +
            "        <${projectArtifactId}.version>${projectVersion}</${projectArtifactId}.version>\n" +
            "        <springboot.version>${springbootVersion}</springboot.version>\n" +
            "    </properties>\n" +
            "\n" +
            "    <modules>\n" +
            "        <module>${projectArtifactId}-domain</module>\n" +
            "        <module>${projectArtifactId}-dao</module>\n" +
            "        <module>${projectArtifactId}-service</module>\n" +
            "        <module>${projectArtifactId}-web</module>\n" +
            "    </modules>\n" +
            "\n" +
            "\n" +
            "    <dependencyManagement>\n" +
            "        <dependencies>\n" +
            "            <dependency>\n" +
            "                <groupId>${projectGroupId}</groupId>\n" +
            "                <artifactId>${projectArtifactId}-domain</artifactId>\n" +
            "                <version>\${${projectArtifactId}.version}</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>${projectGroupId}</groupId>\n" +
            "                <artifactId>${projectArtifactId}-dao</artifactId>\n" +
            "                <version>\${${projectArtifactId}.version}</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>${projectGroupId}</groupId>\n" +
            "                <artifactId>${projectArtifactId}-service</artifactId>\n" +
            "                <version>\${${projectArtifactId}.version}</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>${projectGroupId}</groupId>\n" +
            "                <artifactId>${projectArtifactId}-web</artifactId>\n" +
            "                <version>\${${projectArtifactId}.version}</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>org.springframework.boot</groupId>\n" +
            "                <artifactId>spring-boot-starter-validation</artifactId>\n" +
            "                <version>\${springboot.version}</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>org.springframework.boot</groupId>\n" +
            "                <artifactId>spring-boot-starter-web</artifactId>\n" +
            "                <version>\${springboot.version}</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>org.mybatis.spring.boot</groupId>\n" +
            "                <artifactId>mybatis-spring-boot-starter</artifactId>\n" +
            "                <version>1.3.2</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>org.projectlombok</groupId>\n" +
            "                <artifactId>lombok</artifactId>\n" +
            "                <version>1.16.20</version>\n" +
            "            </dependency>\n" +
            "            <dependency>\n" +
            "                <groupId>mysql</groupId>\n" +
            "                <artifactId>mysql-connector-java</artifactId>\n" +
            "                <version>5.1.21</version>\n" +
            "            </dependency>\n" +
            "        </dependencies>\n" +
            "    </dependencyManagement>\n" +
            "</project>\n"
    val mp = File("${projectArtifactId}/pom.xml")
    mp.writeText(s)
    println("最外层pom.xml <end>")
    println()
}

fun generateDomain() {
    println("domain pom.xml <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    val s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
            "    <modelVersion>4.0.0</modelVersion>\n" +
            "\n" +
            "    <artifactId>${projectArtifactId}-domain</artifactId>\n" +
            "    <version>${projectVersion}</version>\n" +
            "    <packaging>jar</packaging>\n" +
            "\n" +
            "    <parent>\n" +
            "        <groupId>${projectGroupId}</groupId>\n" +
            "        <artifactId>${projectArtifactId}</artifactId>\n" +
            "        <version>${projectVersion}</version>\n" +
            "        <relativePath/> <!-- lookup parent from repository -->\n" +
            "    </parent>\n" +
            "\n" +
            "    <dependencies>\n" +
            "        <dependency>\n" +
            "            <groupId>org.projectlombok</groupId>\n" +
            "            <artifactId>lombok</artifactId>\n" +
            "        </dependency>\n" +
            "    </dependencies>\n" +

            "\n" +
            "</project>\n"
    val domainf = File("${projectArtifactId}/${projectArtifactId}-domain/pom.xml")
    domainf.writeText(s)
    println("domain pom.xml <end>")
    println()
}

fun generateDao() {
    println("dao pom.xml <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    val s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
            "    <modelVersion>4.0.0</modelVersion>\n" +
            "\n" +
            "    <artifactId>${projectArtifactId}-dao</artifactId>\n" +
            "    <version>${projectVersion}</version>\n" +
            "    <packaging>jar</packaging>\n" +
            "\n" +
            "    <parent>\n" +
            "        <groupId>${projectGroupId}</groupId>\n" +
            "        <artifactId>${projectArtifactId}</artifactId>\n" +
            "        <version>${projectVersion}</version>\n" +
            "        <relativePath/> <!-- lookup parent from repository -->\n" +
            "    </parent>\n" +
            "    <dependencies>\n" +
            "        <dependency>\n" +
            "            <groupId>${projectGroupId}</groupId>\n" +
            "            <artifactId>${projectArtifactId}-domain</artifactId>\n" +
            "        </dependency>\n" +
            "        <dependency>\n" +
            "             <groupId>org.mybatis.spring.boot</groupId>\n" +
            "             <artifactId>mybatis-spring-boot-starter</artifactId>\n" +
            "        </dependency>\n" +
            "        <dependency>\n" +
            "             <groupId>mysql</groupId>\n" +
            "             <artifactId>mysql-connector-java</artifactId>\n" +
            "        </dependency>\n" +
            "    </dependencies>\n" +
            "\n" +
            "</project>\n"
    val daof = File("${projectArtifactId}/${projectArtifactId}-dao/pom.xml")
    daof.writeText(s)
    println("dao pom.xml <end>")
    println()

}


fun generateMain() {
    println("web application.java <start>")
    var pp = projectGroupId.split(".").joinToString("/")

    var classname = projectArtifactId[0].toUpperCase().toString()
    for (i in 1..(projectArtifactId.length - 1)) {
        classname += projectArtifactId[i]
    }
    val s = "package ${projectGroupId}.${projectArtifactId}.web;\n" +
            "\n" +
            "import org.mybatis.spring.annotation.MapperScan;\n" +
            "import org.springframework.boot.SpringApplication;\n" +
            "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
            "\n" +
            "@MapperScan(\"${projectGroupId}.${projectArtifactId}.dao\")\n" +
            "@SpringBootApplication\n" +
            "public class ${classname}WebApplication {\n" +
            "\n" +
            "    public static void main(String[] args) {\n" +
            "        SpringApplication.run(${classname}WebApplication.class, args);\n" +
            "    }\n" +
            "}"

    val webmain = File("${projectArtifactId}/${projectArtifactId}-web/src/main/java/${pp}/${projectArtifactId}/web/${classname}WebApplication.java")
    webmain.writeText(s)
    println("web application.java <end>")
    println()
}

fun generateProperties() {
    println("web application.properties <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    val s = "spring.datasource.driver-class-name=com.mysql.jdbc.Driver\n" +
            "\n" +
            "spring.datasource.username=root\n" +
            "\n" +
            "spring.datasource.password=\n" +
            "\n" +
            "spring.datasource.url=jdbc:mysql://localhost:3306/test\n" +
            "\n" +
            "mybatis.type-aliases-package=${projectGroupId}.${projectArtifactId}.domain.po\n" +
            "\n" +
            "mybatis.mapper-locations=classpath:${pp}/${projectArtifactId}/dao/*.xml"
    val f = File("${projectArtifactId}/${projectArtifactId}-web/src/main/resources/application.properties")
    f.writeText(s)
    println("web application.properties <end>")
    println()
}

fun generateService() {
    println("service pom.xml <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    val s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
            "    <modelVersion>4.0.0</modelVersion>\n" +
            "\n" +
            "    <artifactId>${projectArtifactId}-service</artifactId>\n" +
            "    <version>${projectVersion}</version>\n" +
            "    <packaging>jar</packaging>\n" +
            "\n" +
            "    <parent>\n" +
            "        <groupId>${projectGroupId}</groupId>\n" +
            "        <artifactId>${projectArtifactId}</artifactId>\n" +
            "        <version>${projectVersion}</version>\n" +
            "        <relativePath/> <!-- lookup parent from repository -->\n" +
            "    </parent>\n" +
            "    <dependencies>\n" +
            "        <dependency>\n" +
            "            <groupId>${projectGroupId}</groupId>\n" +
            "            <artifactId>${projectArtifactId}-dao</artifactId>\n" +
            "        </dependency>\n" +
            "    </dependencies>\n" +
            "\n" +
            "</project>\n"
    val servicef = File("${projectArtifactId}/${projectArtifactId}-service/pom.xml")
    servicef.writeText(s)
    println("service pom.xml <end>")
println()
}

fun generateWeb() {
    println("web pom.xml <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    val s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
            "    <modelVersion>4.0.0</modelVersion>\n" +
            "\n" +
            "    <artifactId>${projectArtifactId}-web</artifactId>\n" +
            "    <version>${projectVersion}</version>\n" +
            "    <packaging>jar</packaging>\n" +
            "\n" +
            "    <parent>\n" +
            "        <groupId>${projectGroupId}</groupId>\n" +
            "        <artifactId>${projectArtifactId}</artifactId>\n" +
            "        <version>${projectVersion}</version>\n" +
            "        <relativePath/> <!-- lookup parent from repository -->\n" +
            "    </parent>\n" +
            "    <dependencies>\n" +
            "        <dependency>\n" +
            "            <groupId>${projectGroupId}</groupId>\n" +
            "            <artifactId>${projectArtifactId}-service</artifactId>\n" +
            "        </dependency>\n" +
            "        <dependency>\n" +
            "            <groupId>org.springframework.boot</groupId>\n" +
            "            <artifactId>spring-boot-starter-web</artifactId>\n" +
            "            <version>\${springboot.version}</version>\n" +
            "        </dependency>\n" +
            "    </dependencies>\n" +
            "\n" +
            "</project>\n"
    val webf = File("${projectArtifactId}/${projectArtifactId}-web/pom.xml")
    webf.writeText(s)
    println("web pom.xml <end>")
    println()

}


// 连接数据库生成代码
fun connect() {
    println("connecting mysql....")
    var pp = projectGroupId.split(".").joinToString("/")
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    val conn = DriverManager.getConnection("jdbc:mysql://" + hostUrl
            + ":" + mySQLPort + "/" + databaseName, userName, password)

    val st = conn.createStatement()
    val mdc = conn.metaData
    val rsc = mdc.getTables(databaseName, null, "%", null)
    while (rsc.next()) {
        val temp = st.executeQuery("select * from ${rsc.getString(3)}")
        val primaryKeys = mdc.getPrimaryKeys(null, null, rsc.getString(3))
        var count = 0
        while (primaryKeys.next()) {
            count ++
            val pk = primaryKeys.getString("COLUMN_NAME")
            val md = temp.metaData
            val count = md.columnCount
            // 这时候在po中创建一个类
            var lowName = rsc.getString(3)
            var classname = rsc.getString(3)[0].toUpperCase().toString()
            for (i in 1..(rsc.getString(3).length - 1)) {
                classname += rsc.getString(3)[i]
            }
            classname = camelCaseName(classname)
            lowName = camelCaseName(lowName)
            // 生成domain
            writePerDomain(lowName, classname, md, count)
            writeBase()
            writePerCondition(lowName, classname, md, count)
            writePerMapper(lowName, classname, md, count)
            writePerMapperXML(lowName, classname, md, count, pk)
        }
        if (count == 0) print("-----------------------error:${rsc.getString(3)}缺少主键----------------------")
    }

    st.close()
    conn.close()
}

// 在domain包中的condition写入类， 这里继承condition类就行了,
fun writePerCondition(ln: String, name: String, md: ResultSetMetaData, count: Int) {
    println("domain condition <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    // 在domain包中的condition创建相关
    var f = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/condition/${ln}/${name}Condition.java")
    var fn = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/condition/${ln}")
    dirIsExist(fn)
    fileIsExist(f)
    var header = "package ${projectGroupId}.${projectArtifactId}.domain.condition.${ln};\n" +
            "\n" +
            "import lombok.Data;\n" +
            "import ${projectGroupId}.${projectArtifactId}.domain.condition.BaseCondition;"
    "\n"
    var s = "\n" +
            "/**\n" +
            " * @Author: my.zhou\n" +
            " * @Date: 2018/4/25\n" +
            " */\n" +
            "@Data\n" +
            "public class  ${name}Condition extends BaseCondition {\n" + "\n"

    for (i in 1..count) {
        // 这边判断需要添加的
        if ("DATE" == md.getColumnTypeName(i)) {
            header += "import java.util.Date;\n"
        }
        var x = judgeType(md.getColumnTypeName(i))
        s += "    private $x ${camelCaseName(md.getColumnName(i))};\n" +
                "    \n"
    }
    s += "}"
    header += s
    f.writeText(header)
    println("domain condition <end>")
    println()

}

// 写入每个mapper， 这里只需要继承baseMapper类就可以了
fun writePerMapper(ln: String, name: String, md: ResultSetMetaData, count: Int) {
    println("dao mapper interface <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    // 在dao包创建相关
    var f = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/java/${pp}/${projectArtifactId}/dao/${name}Mapper.java")
    var fn = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/java/${pp}/${projectArtifactId}/dao")
    dirIsExist(fn)
    fileIsExist(f)
    var header = "package ${projectGroupId}.${projectArtifactId}.dao;\n" +
            "\n" +
            "import ${projectGroupId}.${projectArtifactId}.domain.condition.${ln}.${name}Condition;\n" +
            "import ${projectGroupId}.${projectArtifactId}.domain.po.${ln}.${name};\n " +
            "\n"
    var s = "\n" +
            "/**\n" +
            " * @Author: my.zhou\n" +
            " * @Date: 2018/4/25\n" +
            " */\n" +
            "public interface ${name}Mapper extends BaseMapper<${name}, ${name}Condition> {\n" + "\n" +
            "}\n"
    f.writeText(header + s)
    println("dao mapper interface <end>")

    println()
}

fun camelCaseName(underscoreName: String?): String {
    var pp = projectGroupId.split(".").joinToString("/")
    val result = StringBuilder()
    if (underscoreName != null && underscoreName.length > 0) {
        var flag = false
        for (i in 0 until underscoreName.length) {
            val ch = underscoreName[i]
            if ("_"[0] == ch) {
                flag = true
            } else {
                if (flag) {
                    result.append(Character.toUpperCase(ch))
                    flag = false
                } else {
                    result.append(ch)
                }
            }
        }
    }
    return result.toString()
}


// 写入每个mapper.xml
fun writePerMapperXML(ln: String, name: String, md: ResultSetMetaData, count: Int, pk: String) {
    println("dao mapper.xml <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    // 在dao中的resources中创建相关
    var f = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/resources/${pp}/${projectArtifactId}/dao/${name}Mapper.xml")
    var fn = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/resources/${pp}/${projectArtifactId}/dao")
    dirIsExist(fn)
    fileIsExist(f)
    var header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
            "<mapper namespace=\"${projectGroupId}.${projectArtifactId}.dao.${name}Mapper\">\n" +
            "\n" +
            "\n" +
            "\t<resultMap id=\"${name}\" type=\"${projectGroupId}.${projectArtifactId}.domain.po.${ln}.${name}\">\n"
    var resultmap = ""
    var propertySql = ""
    var whereSql = ""
    for (i in 1..count) {
        var jdbcType: String = justifyJdbcType(md.getColumnTypeName(i))

        resultmap += "\t\t<result column=\"${md.getColumnName(i)}\" property=\"${camelCaseName(md.getColumnName(i))}\" jdbcType=\"${jdbcType}\"/>\n"
        propertySql += if (i == count) {
            "\t\t  t.${md.getColumnName(i)}\n"
        } else {
            "\t\t  t.${md.getColumnName(i)},\n"
        }
        whereSql += if ("VARCHAR" == md.getColumnTypeName(i)) {
            "\t\t<if test=\"${camelCaseName(md.getColumnName(i))} != null and ${camelCaseName(md.getColumnName(i))} != ''\">\n" +
                    "\t\t\tAND t.${md.getColumnName(i)} = #{${camelCaseName(md.getColumnName(i))},jdbcType = ${jdbcType}}\n" +
                    "\t\t</if>\n"
        } else {
            "\t\t<if test=\" ${camelCaseName(md.getColumnName(i))} != null\">\n" +
                    "\t\t\tAND t.${md.getColumnName(i)} = #{${camelCaseName(md.getColumnName(i))},jdbcType = ${jdbcType}}\n" +
                    "\t\t</if>\n"
        }
    }
    var jdbcTypeSelect: String = ""
    for (i in 1..count) {
        if (pk == md.getColumnName(i)) {
            jdbcTypeSelect = md.getColumnTypeName(i)
        }
    }
    var ss = "\t<select id=\"select\" parameterType=\"String\" resultMap=\"${name}\">\n" +
            "\t\tSELECT \n" +
            "\t\t<include refid=\"fieldSql\"/>\n" +
            "\t\t  FROM ${ln} t \n" +
            "\t\t WHERE t.$pk = #{${camelCaseName(pk)},jdbcType = ${justifyJdbcType(jdbcTypeSelect)}} \n" +
            "\t</select>" +
            "\n"
    var ssc = "\t<select id=\"count\" parameterType=\"${projectGroupId}.${projectArtifactId}.domain.condition.${ln}.${name}Condition\" resultType=\"Integer\">\n" +
            "\t\tSELECT\n" +
                    "\t\tcount(1)\n" +
                    "\t\t  FROM ${ln} t\n" +
                    "\t\t WHERE 1=1\n" +
                    "\t\t <include refid=\"whereSql\"/>\n" +
                    "\t</select>" + "\n"

    var ssl = "\n" +
            "\t<select id=\"selectList\" parameterType=\"${projectGroupId}.${projectArtifactId}.domain.condition.${ln}.${name}Condition\" resultMap=\"${name}\">\n" +
            "\t\tSELECT \n" +
            "\t\t<include refid=\"fieldSql\"/>\n" +
            "\t\t  FROM ${ln} t \n" +
            "\t\t WHERE 1=1\n" +
            "\t\t<include refid=\"whereSql\"/>\n" +
            "\t</select>" +"\n"

    var insertf = ""
    var insertv = ""
    for (i in 1..count) {
        if (md.getColumnName(i) != pk) {
            insertf += "\t\t<if test=\"${camelCaseName(md.getColumnName(i))} != null\">\n" +
                    "\t\t\t,${md.getColumnName(i)}\n" +
                    "\t\t</if>\n"
            insertv +=
                    "\t\t<if test=\"${camelCaseName(md.getColumnName(i))} != null\">\n" +
                    "\t\t\t,#{${camelCaseName(md.getColumnName(i))},jdbcType=${justifyJdbcType(md.getColumnTypeName(i))}}\n" +
                    "\t\t</if>\n"
        }
    }

    var ins = "\n" +
            "\t<insert id=\"insert\" parameterType=\"${projectGroupId}.${projectArtifactId}.domain.po.${ln}.${name}\">\n" +
            "\t\tINSERT INTO ${ln} (  \n" +
            "\t\t\t${pk}\n" +
            insertf +
            "\t\t)\n" +
            "\t\tVALUES( \n" +
            "\t\t\t\${${pk}, jdbcType=${justifyJdbcType(jdbcTypeSelect)}}\n" +
            insertv +
            "\t\t)\n" +
            "\t</insert>\n"
    var ds =
            "\n" +
                    "\t<delete id=\"delete\" parameterType=\"String\">\n" +
                    "\t\t DELETE FROM ${ln}\n" +
                    "\t\t  WHERE $pk = #{${camelCaseName(pk)},jdbcType=${justifyJdbcType(jdbcTypeSelect)}} \n" +
                    "\t</delete>"
    var usv = ""
    for (i in 1..count) {
        if (pk == md.getColumnName(i)) continue
        usv += "\t\t    <if test=\"${camelCaseName(md.getColumnName(i))} != null\">\n" +
                "\t\t\t    t.${md.getColumnName(i)} = #{${camelCaseName(md.getColumnName(i))},jdbcType = ${justifyJdbcType(md.getColumnTypeName(i))}}, \n" +
                "\t\t    </if>\n"
    }

    var us = "\n\n" + "\t<update id=\"update\" parameterType=\"${projectGroupId}.${projectArtifactId}.domain.po.${ln}.${name}\">\n" +
            "\t\tUPDATE ${ln} t\n" +
            "\t\t <set>\n" +
            usv +
            "\t    </set>\n" +
            "\t\t WHERE t.$pk= #{${camelCaseName(pk)},jdbcType = ${justifyJdbcType(jdbcTypeSelect)}}\n" +
            "\t</update>"

    var rs = header + resultmap +
            "\t</resultMap>\n" +
            "\n" +
            "\t<sql id=\"fieldSql\">\n" +
            propertySql +
            "\t</sql>\n" +
            "\n" +
            "\t<sql id=\"whereSql\">\n" +
            whereSql +
            "\t</sql>\n" +
            "\n" +
            ss +
            ssc +
            ssl +
            ins +
            ds +
            us +
            "\n" +
            "</mapper>"
    f.writeText(rs)
    println("dao mapper.xml <end>")
    println()
}

// 写入每个domain
fun writePerDomain(ln: String, name: String, md: ResultSetMetaData, count: Int) {
    println("domain po <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    // 创建文件， 在domain包中的po中创建相关的
    var f = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/po/${ln}/${name}.java")
    var fn = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/po/${ln}")
    dirIsExist(fn)
    fileIsExist(f)
    var header = "package ${projectGroupId}.${projectArtifactId}.domain.po.${ln};\n" +
            "\n" +
            "import lombok.Data;\n"
    "\n"
    var s = "\n" +
            "/**\n" +
            " * @Author: my.zhou\n" +
            " * @Date: 2018/4/25\n" +
            " */\n" +
            "@Data\n" +
            "public class  ${name} {\n" + "\n"
    for (i in 1..count) {
        // 这边判断需要添加的
        if ("DATE" == md.getColumnTypeName(i)) {
            header += "import java.util.Date;\n"
        }
        var x = judgeType(md.getColumnTypeName(i))
        s += "    private $x ${camelCaseName(md.getColumnName(i))};\n" +
                "    \n"
    }
    s += "}"
    header += s
    f.writeText(header)
    println("domain po <end>")
    println()
}


// 这里写入两个base类
fun writeBase() {
    println("mapper condition base <start>")
    var pp = projectGroupId.split(".").joinToString("/")
    // 生成mapper
    var baseMapper = "package ${projectGroupId}.${projectArtifactId}.dao;\n" +
            "\n" +
            "import ${projectGroupId}.${projectArtifactId}.domain.condition.BaseCondition;\n" +
            "\n" +
            "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * @Author: my.zhou\n" +
            " * @Date: 2018/4/25\n" +
            " */\n" +
            "\n" +
            "public interface BaseMapper<T,C extends BaseCondition> {\n" +
            "    /**\n" +
            "     * 获得bean\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    T select(Object id);\n" +
            "    /**\n" +
            "     * 获得数量\n" +
            "     *\n" +
            "     * @param condition\n" +
            "     * @return\n" +
            "     */\n" +
            "    int count(C condition);\n" +
            "    /**\n" +
            "     * 获得列表\n" +
            "     *\n" +
            "     * @param condition\n" +
            "     * @return\n" +
            "     */\n" +
            "    List<T> selectList(C condition);\n" +
            "    /**\n" +
            "     * 新增记录\n" +
            "     *\n" +
            "     * @param po\n" +
            "     */\n" +
            "    void insert(T po);\n" +
            "    /**\n" +
            "     * 修改记录\n" +
            "     *\n" +
            "     * @param po\n" +
            "     * @return\n" +
            "     */\n" +
            "    int update(T po);\n" +
            "    /**\n" +
            "     * 删除记录\n" +
            "     *\n" +
            "     * @param id\n" +
            "     * @return\n" +
            "     */\n" +
            "    int delete(Object id);\n" +
            "}\n" +
            "\n"
    var baseCondition = "package ${projectGroupId}.${projectArtifactId}.domain.condition;\n" +
            "\n" +
            "public class BaseCondition {\n" +
            "\n" +
            "    private int pageSize = 0;\n" +
            "    private int pageNum;\n" +
            "    private int skipResults = 0;\n" +
            "\n" +
            "    private String orderBy;\n" +
            "    public int getPageSize() {\n" +
            "        return pageSize;\n" +
            "    }\n" +
            "\n" +
            "    public void setPageSize(int pageSize) {\n" +
            "        this.pageSize = pageSize;\n" +
            "        skipResults = pageSize * pageNum;\n" +
            "    }\n" +
            "\n" +
            "    public int getPageNum() {\n" +
            "        return pageNum;\n" +
            "    }\n" +
            "\n" +
            "    public void setPageNum(int pageNum) {\n" +
            "        this.pageNum = pageNum;\n" +
            "        skipResults = pageSize * pageNum;\n" +
            "    }\n" +
            "\n" +
            "    public void setSkipResults(int skipResults) {\n" +
            "        this.skipResults = skipResults;\n" +
            "    }\n" +
            "\n" +
            "    public int getSkipResults() {\n" +
            "        return skipResults;\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    public String getOrderBy() {\n" +
            "        return orderBy;\n" +
            "    }\n" +
            "\n" +
            "    public void setOrderBy(String orderBy) {\n" +
            "        this.orderBy = orderBy;\n" +
            "    }\n" +
            "}\n"
    var f1 = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/java/${pp}/${projectArtifactId}/dao/BaseMapper.java")
    var f2 = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/condition/BaseCondition.java")
    var f2n = File("${projectArtifactId}/${projectArtifactId}-domain/src/main/java/${pp}/${projectArtifactId}/domain/condition")
    var f1n = File("${projectArtifactId}/${projectArtifactId}-dao/src/main/java/${pp}/${projectArtifactId}/dao")
    dirIsExist(f1n)
    dirIsExist(f2n)
    fileIsExist(f1)
    fileIsExist(f2)
    f1.writeText(baseMapper)
    f2.writeText(baseCondition)
    println("mapper condition base <end>")
    println()
}

fun judgeType(type: String): String? {
    val map = mapOf("INT" to "Integer"
            , "VARCHAR" to "String"
            , "BIGINT" to "Long"
            , "BLOB" to "byte[]"
            , "CHAR" to "String"
            , "DATE" to "Date"
            , "DATETIME" to "Timestamp"
            , "DECIMAL" to "BigDecimal"
            , "DOUBLE" to "Double"
            , "ENUM" to "String"
            , "FLOAT" to "Float"
            , "INTEGER" to "INTEGER"
            , "TINYINT" to "Integer"
    )
    return map[type]
}


fun justifyJdbcType(input: String): String {
    return when (input) {
        "INT" -> "INTEGER"
        "BIGINT" -> "BIGINT"
        "BIT" -> "BIT"
        "BLOB" -> "BLOB"
        "CHAR" -> "CHAR"
        "TEXT" -> "TEXT"
        "DATE" -> "DATE"
        "DECIMAL" -> "DECIMAL"
        "DOUBLE" -> "DOUBLE"
        "FLOAT" -> "FLOAT"
        "INTEGER" -> "INTEGER"
        "NUMERIC" -> "NUMERIC"
        "REAL" -> "REAL"
        "SMALLINT" -> "SMALLINT"
        "TIME" -> "TIME"
        "TIMESTAMP" -> "TIMESTAMP"
        "TINYINT" -> "TINYINT"
        "VARCHAR" -> "VARCHAR"

        else -> {
            ""
        }
    }
}
//
//fun main(args: Array<String>) {
//
//    println("请输入maven的groupId：")
//    val sc = Scanner(System.`in`)
//    projectGroupId = sc.next()
//    println("请输入maven的ArtifactId：")
//    projectArtifactId = sc.next()
//    println("请输入maven的版本号(默认为1.0.0.SNAPSHOT<输入N>):")
//    projectVersion = sc.next()
//    if (projectVersion == "N") projectVersion = "1.0.0.SNAPSHOT"
//    println("请输入springboot的版本号(默认为2.0.1.RELEASE<输入N>):")
//    springbootVersion = sc.next()
//    if (springbootVersion == "N") springbootVersion = "2.0.1.RELEASE"
//    println("请输入数据库的地址(默认为127.0.0.1<输入N>):")
//    hostUrl = sc.next()
//    if (hostUrl == "N") hostUrl = "127.0.0.1"
//    println("请输入数据库的端口号(默认为3306<输入N>):")
//    mySQLPort = sc.next()
//    if (mySQLPort == "N") mySQLPort = "3306"
//    println("请输入数据库的名称(默认test<输入N>):")
//    databaseName = sc.next()
//    println("请输入数据库的用户名(默认为root<输入N>):")
//    userName = sc.next()
//    if (userName == "N") userName = "root"
//    println("请输入数据库的密码(默认为无<输入N>):")
//    password = sc.next()
//    if (password == "N") password = ""
////    projectGroupId = "com.zmy"
////
////    projectArtifactId = "cosmetic"
////
////    projectVersion = "1.0.0.SNAPSHOT"
////
////    springbootVersion = "2.0.1.RELEASE"
////
////    databaseName = "test"
////    userName = "root"
////    password = ""
////    mySQLPort = "3306"
////    hostUrl = "127.0.0.1"
//    // 参数有 groupId architId projectVersion springbootVersion
//    // 数据库地址， database， 用户名 密码， 端口
//    createStructure()
//    connect()
//}