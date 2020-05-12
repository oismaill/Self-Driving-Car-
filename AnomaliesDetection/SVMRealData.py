#from sklearn import preprocessing , neighbors
from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd
from sklearn.svm import SVC
import matplotlib.pyplot as plt
from mlxtend.plotting import plot_decision_regions
from sklearn.model_selection import cross_val_score
#from sklearn.naive_bayes import GaussianNB
from sklearn import metrics
#from sklearn.neighbors import KNeighborsClassifier
#from sklearn.tree import DecisionTreeClassifier # Import Decision Tree Classifier

df = pd.read_csv('C:/Users/modyn/Desktop/savgol.csv')
X = np.array(df.drop(['Label'],1))
#X = X[:, [0, 2]]

y = np.array(df['Label'])
#X_train , X_test , y_train , y_test = train_test_split(X,y,test_size=0.3)
#NaiveBayes
# X_train , X_test , y_train , y_test = train_test_split(X,y,test_size=0.3)
# gnb = GaussianNB()
# gnb.fit(X_train, y_train)
# accuracy = gnb.score(X_test,y_test)
# print("NaiveBayes Accuracy",accuracy)
sum10 = 0
sum50 = 0
accuracy10 = 0
accuracy50 = 0
#Guassian Kernel function

X_train2, X_test2, y_train2, y_test2 = train_test_split(X, y, test_size=0.3)
svclassifier = SVC(kernel='rbf', C=10, gamma=235)  # 0.709% c=10,G=200..... C50 is better
svclassifier.fit(X_train2, y_train2)
# scores = cross_val_score(svclassifier, X, y, cv=8)
y_pred = svclassifier.predict(X_test2)
accuracy10= metrics.accuracy_score(y_test2, y_pred)
print("RBF SVM Accuracy:", accuracy10 )
    #713365 avg for c=10 g=200
    #714249 avg for c=10 g=230
    #7174453742445375 avg c=10 g =235
    #7167945141794514 avg c=10 g =236
    #sum10 += accuracy10
#print("RBF SVM avg:", sum10/10 )

#svclassifier.fit(X_train2, y_train2)
#svclassifier.fit(X_train2, y_train2)

#fig, ax = plt.subplots()
#value=1.5
#width = 0.75
#plot_decision_regions(X, y, clf=svclassifier,filler_feature_values={2: value , 3: value},
#                     filler_feature_ranges={2: width, 3: width}, legend=2,ax=ax)

# Adding axes annotations
#ax.set_xlabel('Feature 1')
#ax.set_ylabel('Feature 2')
#ax.set_title('Feature 3 = {}'.format(value))

# Adding axes annotations
#fig.suptitle('SVM on make_blobs')
#plt.show()
#Create KNN Classifier
# X_train3 , X_test3 , y_train3 , y_test3 = train_test_split(X,y,test_size=0.3)
# knn = KNeighborsClassifier(n_neighbors=15)
# knn.fit(X_train3, y_train3)
# accuracy = knn.score(X_test3,y_test3)
# print("KNN Accuracy",accuracy)
# # Desicision Tree
# X_train4 , X_test4 , y_train4 , y_test4 = train_test_split(X,y,test_size=0.3)
# clf = DecisionTreeClassifier()
# clf = clf.fit(X_train4,y_train4)
# pred = clf.predict(X_test4)
# print("DecisionTree Accuracy:",metrics.accuracy_score(y_test4, pred))

#print("Accuracy: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))
#print(scores)
#example = np.array([AX, AY, AZ, GX, GY, GZ])  # LIST OF LISTS
#example = example.reshape(1,-1)
#prediction = svclassifier.predict(example)
#if(prediction == 0):
#    print("CLEAN ROAD!")
#elif (prediction == 1):
#    print("ANOMALY DETECTED!!!")
#clf = neighbors.KNeighborsClassifier()
#clf.fit(X_train,y_train)
#accuracy = clf.score(X_test,y_test)
#print("Accuracy" , accuracy)
#if(prediction == 0):
#   print("CLEAN ROAD!")
#elif (prediction == 1):
#    print("BUMP DETECTED!!!")