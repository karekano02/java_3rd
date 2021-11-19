<?xml version="1.0" encoding="UTF-8"?>

<config xmlns="http://www.navercorp.com/lucy-xss-servlet">
    <defenders>
        <!-- XssPreventer 등록 -->
        <defender>
            <name>xssPreventerDefender</name>
            <class>com.navercorp.lucy.security.xss.servletfilter.defender.XssPreventerDefender</class>
        </defender>

        <!-- XssSaxFilter 등록 -->
        <defender>
            <name>xssSaxFilterDefender</name>
            <class>com.navercorp.lucy.security.xss.servletfilter.defender.XssSaxFilterDefender</class>
            <init-param>
                <param-value>lucy-xss-sax.xml</param-value>   <!-- lucy-xss-filter의 sax용 설정파일 -->
                <param-value>false</param-value>        <!-- 필터링된 코멘트를 남길지 여부, 성능 효율상 false 추천 -->
            </init-param>
        </defender>

        <!-- XssFilter 등록 -->
        <defender>
            <name>xssFilterDefender</name>
            <class>com.navercorp.lucy.security.xss.servletfilter.defender.XssFilterDefender</class>
            <init-param>
                <param-value>lucy-xss.xml</param-value>    <!-- lucy-xss-filter의 dom용 설정파일 -->
                <param-value>false</param-value>         <!-- 필터링된 코멘트를 남길지 여부, 성능 효율상 false 추천 -->
            </init-param>
        </defender>
    </defenders>

    <!-- default defender 선언, 필터링 시 지정한 defender가 없으면 여기 정의된 default defender를 사용해 필터링 한다. -->
    <default>
        <defender>xssPreventerDefender</defender>
    </default>

    <!-- global 필터링 룰 선언 -->
    <global>
        <!-- 모든 url에서 들어오는 globalParameter 파라메터는 필터링 되지 않으며
                또한 globalPrefixParameter1로 시작하는 파라메터도 필터링 되지 않는다.
                globalPrefixParameter2는 필터링 되며 globalPrefixParameter3은 필터링 되지 않지만
                더 정확한 표현이 가능하므로 globalPrefixParameter2, globalPrefixParameter3과 같은 불분명한 표현은 사용하지 않는 것이 좋다. -->
<!--        <params>
            <param name="globalParameter" useDefender="false" />
            <param name="globalPrefixParameter1" usePrefix="true" useDefender="false" />
            <param name="globalPrefixParameter2" usePrefix="true" />
            <param name="globalPrefixParameter3" usePrefix="false" useDefender="false" />
        </params>-->
        <params>
            <param name="gParam" useDefender="false" />
            <param name="g" usePrefix="true" useDefender="false" />
        </params>

    </global>

    <url-rule-set>
        <!-- url disable이 true이면 지정한 url 내의 모든 파라미터는 필터링되지 않는다.(기본값은 false) -->
        <url-rule>
            <url disable="false">/xss/noneFilter</url>
        </url-rule>
        <!--
        1. '/xss/globalFilter.do' 내의 nParam은 필터링 되지 않는다.
        2. '/xss/globalFilter.do' 내의 'n'으로 시작하는 파라미터는 필터링 되지 않는다.(usePrefix=true)
        -->
        <url-rule>
            <url>/xss/globalFilter.do</url>
            <params>
                <param name="nParam" useDefender="false" />
                <param name="n" usePrefix="true" useDefender="false" />
            </params>
        </url-rule>
    </url-rule-set>

    <!-- url 별 필터링 룰 선언 -->
<!--    <url-rule-set>-->

        <!-- url disable이 true이면 지정한 url 내의 모든 파라메터는 필터링 되지 않는다. -->
<!--        <url-rule>
            <url disable="true">/disableUrl1.do</url>
        </url-rule>-->

        <!-- url disable이 false인 설정은 기본이기 때문에 불필요하다. 아래와 같은 불필요한 설정은 하지 않는다.-->
<!--        <url-rule>
            <url disable="false">/disableUrl2.do</url>
        </url-rule>-->

        <!-- url disable이 true이면 지정한 url 내의 모든 파라메터가 필터링 되지 않기 때문에 <params> 로 선언한 설정은 적용되지 않는다.
               아래와 같은 불필요한 설정은 하지 않는다. -->
  <!--      <url-rule>
            <url disable="true">/disableUrl3.do</url>
            <params>
                <param name="query" useDefender="false" />
                <param name="prefix1" usePrefix="true" />
                <param name="prefix2" usePrefix="false" useDefender="false" />
                <param name="prefix3" usePrefix="true" useDefender="true" />
                <param name="prefix4" usePrefix="true" useDefender="false" />
                <param name="prefix5" usePrefix="false" useDefender="true" />
            </params>
        </url-rule>-->

        <!-- url disable이 false인 설정은 기본이기 때문에 불필요하다. <params> 선언한 설정은 적용이 된다.-->
  <!--      <url-rule>
            <url disable="false">/disableUrl4.do</url>
            <params>
                &lt;!&ndash; disableUrl4.do 의 query 파라메터와 prefix4로 시작하는 파라메터들은 필터링 되지 않는다.
                        usePrefix가 false, useDefender가 true인 설정은 기본이기 때문에 불필요하다. &ndash;&gt;
                <param name="query" useDefender="false" />
                <param name="prefix1" usePrefix="true" />
                <param name="prefix2" usePrefix="false" useDefender="false" />
                <param name="prefix3" usePrefix="true" useDefender="true" />
                <param name="prefix4" usePrefix="true" useDefender="false" />
                <param name="prefix5" usePrefix="false" useDefender="true" />
                <param name="prefix6" usePrefix="true">
                    <defender>xssSaxFilterDefender</defender>
                </param>
            </params>
        </url-rule>-->

        <!-- url1 내의 url1Parameter는 필터링 되지 않으며 또한 url1PrefixParameter로 시작하는 파라메터도 필터링 되지 않는다. globalParameter는
                상위 글로벌 global 설정에 동일한 이름으로 되어있지만 url-rule 설정을 더 우선하여 따른다. -->
<!--        <url-rule>
            <url>/url1.do</url>
            <params>
                <param name="url1Parameter" useDefender="false" />
                <param name="url1PrefixParameter" usePrefix="true" useDefender="false" />
                <param name="globalParameter"/>
            </params>
        </url-rule>-->

        <!-- url2 내의 url2Parameter1만 필터링 되지 않으며 url2Parameter2는 xssSaxFilterDefender를 사용해 필터링 한다.  -->
<!--        <url-rule>
            <url>/url2.do</url>
            <params>
                <param name="url2Parameter1" useDefender="false">
                    <defender>xssPreventerDefender</defender>
                </param>
                <param name="url2Parameter2">
                    <defender>xssSaxFilterDefender</defender>
                </param>
                <param name="url2Parameter3">
                    <defender>xssPreventerDefender</defender>
                </param>
            </params>
        </url-rule>-->
<!--    </url-rule-set>-->
</config>
