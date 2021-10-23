#Import scikit-learn dataset library
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import classification_report, confusion_matrix
import pandas as pd
import pymysql
from sqlalchemy import create_engine
import flask
from flask import request, jsonify

app = flask.Flask(__name__)
app.config["DEBUG"] = True


@app.route('/riskmodel', methods=['GET'])
def home():

    try:
        connection = pymysql.connect(host='healthygymdbinstance.cg2cefdnsxhu.us-east-2.rds.amazonaws.com',
                                 user='admin',
                                 password='Luka77doncic',
                                 db='healthygymdb1')

        db_Info = connection.get_server_info()
        print("Connected to MySQL Server version ", db_Info)
        cursor = connection.cursor()
        cursor.execute("select DAYOFWEEK(a.checkin_timestamp) as day_of_week, " + 
            "HOUR(a.checkin_timestamp) as hour_value, a.risk_class as risk_class" + 
            "from healthygymdb1.checkins a")
        records = cursor.fetchall()
        print("Total number of rows in table: ", cursor.rowcount)

        df = pd.DataFrame(records, columns=['day_of_week', 'hour_value' , 'risk_class'])
        #print(df)

        X=df.drop('risk_class', axis=1)
        #print(X)
        y = df['risk_class']

        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.10)
        svclassifier = SVC(kernel='rbf')
        svclassifier.fit(X_train, y_train)

        #print(y_test)

        y_pred = svclassifier.predict(X_test)

        print(confusion_matrix(y_test,y_pred))

        cursor.execute("truncate healthygymdb1.riskdetail")

        rows = []
        for i in range(7):
            a=i+1
            for j in range(24):
                b=j+1
                rows.append([a, b])
                #print(rows)

        testDF = pd.DataFrame(rows, columns=["day_of_week", "hour_value"])

        testDF_pred = svclassifier.predict(testDF)

        testDF['risk_class'] = testDF_pred
        testDF['id'] = 0

        c=0

        for index, row in testDF.iterrows():
            c = c + 1
            row['id'] = c

        #print(testDF)

        engine = create_engine("mysql+pymysql://{user}:{pw}@healthygymdbinstance.cg2cefdnsxhu.us-east-2.rds.amazonaws.com/{db}"
                           .format(user="admin",
                                   pw="Luka77doncic",
                                   db="healthygymdb1"))

        testDF.to_sql('riskdetail', con = engine, if_exists = 'append', index=False, chunksize = 1000)

    except Error as e:
        return "Error while connecting to MySQL" + e
    finally:
        #if connection.is_connected():
        cursor.close()
        connection.close()
        return "Update success.  MySQL connection is closed"

app.run(host="0.0.0.0",port=5000)

