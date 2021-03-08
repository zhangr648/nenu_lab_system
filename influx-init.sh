database=nenu_phy

curl -i -XPOST "localhost:8086/query" --data-urlencode "q=

CREATE DATABASE $database

CREATE RETENTION POLICY \"one_month\" ON \"$database\" DURATION 30d REPLICATION 1 DEFAULT
CREATE RETENTION POLICY \"one_year\" ON \"$database\" DURATION 365d REPLICATION 1

CREATE CONTINUOUS QUERY \"cq_half_hour_mean\" ON \"$database\"
BEGIN
  SELECT mean(*)
  INTO \"$database\".\"one_year\".:MEASUREMENT
  FROM /.*/ GROUP BY time(30m),*
END;

"