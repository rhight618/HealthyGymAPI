#Import scikit-learn dataset library
from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import classification_report, confusion_matrix
import mysql.connector
from mysql.connector import Error
import pandas as pd
import datetime as dt

#database connection

try:
    connection = mysql.connector.connect(host='healthygymdbinstance.cg2cefdnsxhu.us-east-2.rds.amazonaws.com',
    									 port=3306,
                                         database='healthygymdb1',
                                         user='admin',
                                         password='Luka77doncic',
                                         use_pure=True)
    if connection.is_connected():
        db_Info = connection.get_server_info()
        print("Connected to MySQL Server version ", db_Info)
        cursor = connection.cursor()
        cursor.execute("select DAYOFWEEK(a.checkin_timestamp) as checkin_weekday, HOUR(a.checkin_timestamp) as checkin_hour, a.risk_class as class from healthygymdb1.checkins a")
        records = cursor.fetchall()
        print("Total number of rows in table: ", cursor.rowcount)

        df = pd.DataFrame(records, columns=['checkin_weekday', 'checkin_hour' , 'risk_class'])
        #df['checkin_ts'] = pd.to_datetime(df['checkin_ts'])
        #df['checkin_ts'] = df['checkin_ts'].dt.strftime("%Y%m%d%H%M%S")
        #df = df.drop('checkin_id', axis=1)
        #pd.set_option('display.max_rows', df.shape[0]+1)
        #df['checkin_ts'] = pd.to_datetime(df['checkin_ts'])
        #df['time'],df['date']= df['checkin_ts'].apply(lambda x:x.time()), df['checkin_ts'].apply(lambda x:x.date())
        #df['date']=df['date'].map(dt.datetime.toordinal)
        #df = df.drop('checkin_ts', axis=1)
        print(df)

        X=df.drop('risk_class', axis=1)
        print(X)

        #X['checkin_ts']=X['checkin_ts'].map(dt.datetime.toordinal)
        #print(X['checkin_ts'])
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
        		#print(rows)

        testDF = pd.DataFrame(rows, columns=["DOW", "HOUR"])
        #print(testDF)

        y_pred = svclassifier.predict(X_test)

        print(confusion_matrix(y_test,y_pred))
        print(classification_report(y_test,y_pred))

        testDF_pred = svclassifier.predict(testDF)
        print(testDF_pred)

        testDF['class'] = testDF_pred
        print(testDF)

      #  for row in records:
	  #      print("Id = ", row[0], )
	  #      print("checkin_ts = ", row[1])
	  #      print("user_id = ", row[2])
	  #      print("risk_class  = ", row[3], "\n")

except Error as e:
    print("Error while connecting to MySQL", e)
finally:
    if connection.is_connected():
        cursor.close()
        connection.close()
        print("MySQL connection is closed")


