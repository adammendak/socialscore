# socialscore

to run app type: <br>
java -jar socialscore-1.0.jar

Example flow from specification : 
<br>
ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20 <br>
ADD https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/ 30 <br>

ADD http://www.bbc.com/news/world-europe-45746837 10 <br>

EXPORT creates file with data inside: <br>
domain;urls;social_score <br>
rte.ie;2;50 <br>
bbc.com;1;10<br>

REMOVE https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/ <br>

EXPORT creates file with <br>
domain;urls;social_score  <br>
rte.ie;1;20  <br>
bbc.com;1;10  <br>