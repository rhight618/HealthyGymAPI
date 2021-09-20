#Import scikit-learn dataset library
from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import classification_report, confusion_matrix
import mysql.connector
from mysql.connector import Error
from pandas.io import sql
import pandas as pd
import pymysql
from sqlalchemy import create_engine

#database connection

try:
    connection = pymysql.connect(host='healthygymdbinstance.cg2cefdnsxhu.us-east-2.rds.amazonaws.com',
                             user='admin',
                             password='Luka77doncic',
                             db='healthygymdb1')

    #if connection.is_connected():
    db_Info = connection.get_server_info()
    print("Connected to MySQL Server version ", db_Info)
    cursor = connection.cursor()
    cursor.execute("select DAYOFWEEK(a.checkin_timestamp) as checkin_weekday, HOUR(a.checkin_timestamp) as checkin_hour, a.risk_class as class from healthygymdb1.checkins a")
    records = cursor.fetchall()
    print("Total number of rows in table: ", cursor.rowcount)

    df = pd.DataFrame(records, columns=['checkin_weekday', 'checkin_hour' , 'risk_class'])
    print(df)

    X=df.drop('risk_class', axis=1)
    print(X)
    y = df['risk_class']

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.10)
    svclassifier = SVC(kernel='rbf')
    svclassifier.fit(X_train, y_train)

    #print(y_test)

    cursor.execute("truncate healthygymdb1.riskdetail")

    rows = []
    for i in range(7):
    	a=i+1
    	for j in range(24):
    		b=j+1
    		rows.append([a, b])
    		print(rows)

    testDF = pd.DataFrame(rows, columns=["day_of_week", "hour_value"])
    #print(testDF)

    y_pred = svclassifier.predict(X_test)

    print(confusion_matrix(y_test,y_pred))
    print(classification_report(y_test,y_pred))

    testDF_pred = svclassifier.predict(testDF)
    #print(testDF_pred)

    testDF['risk_class'] = testDF_pred

    testDF['id'] = 0

    c=0

    for index, row in testDF.iterrows():
        c = c + 1
        row['id'] = c

    print(testDF)

    engine = create_engine("mysql+pymysql://{user}:{pw}@healthygymdbinstance.cg2cefdnsxhu.us-east-2.rds.amazonaws.com/{db}"
                       .format(user="admin",
                               pw="Luka77doncic",
                               db="healthygymdb1"))

    testDF.to_sql('riskdetail', con = engine, if_exists = 'append', index=False, chunksize = 1000)

except Error as e:
    print("Error while connecting to MySQL", e)
finally:
    #if connection.is_connected():
    cursor.close()
    connection.close()
    print("MySQL connection is closed")
