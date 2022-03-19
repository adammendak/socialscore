# socialscore


ADD http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20
ADD https://www.rte.ie/news/ulster/2018/1004/1000952-moanghan-mine/ 30

ADD http://www.bbc.com/news/world-europe-45746837 10

EXPORT creates file with
domain;urls;social_score
rte.ie;2;50
bbc.com;1;10

REMOVE http://www.rte.ie/news/politics/2018/1004/1001034-cso/ 20

EXPORT creates file with 
domain;urls;social_score
rte.ie;1;20
bbc.com;1;10