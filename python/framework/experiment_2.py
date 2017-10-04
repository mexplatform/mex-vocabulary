import numpy as np
from sklearn.datasets import load_iris
from sklearn.svm import SVC
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn.model_selection import cross_val_predict
from sklearn import metrics
import mex_framework


@mex_framework.experiment_info
def set_experiment_info():
    author = 'Igor Costa'
    email = 'igorsc93@ime.eb.br'
    title = 'SVM Experiment'
    tags = ['Scikit Learn', 'Python 2.7', 'MEX', 'Iris']

    return {'author': author, 'email': email, 'title': title, 'tags': tags}

@mex_framework.hardware
def set_hardware_info():
    cpu = 'Intel Core i7'
    memory = '16 GB'
    hd = 'SSD'
    cache = '6M'
    os = 'macOS Sierra 10.12'
    video = 'Nvidia GeForce GT750M'
    return  {'cpu': cpu, 'memory': memory, 'hd': hd, 'cache': cache, 'os': os, 'video': video}


iris = load_iris()

X = iris.data
y = iris.target

@mex_framework.dataset_name
def set_dataset_name():
    name = 'Iris'
    URI = 'http://archive.ics.uci.edu/ml/datasets/Iris'
    description = 'This is perhaps the best known database to be found in the pattern recognition literature. ' \
                  'Fisher\'s paper is a classic in the field and is referenced frequently to this day. (See Duda & Hart, for example.) ' \
                  'The data set contains 3 classes of 50 instances each, where each class refers to a type of iris plant. One class is linearly ' \
                  'separable from the other 2; the latter are NOT linearly separable from each other.'
    return  {'name': name,'URI': URI,'description': description }

@mex_framework.features
def set_features():
    features = iris.feature_names
    return {'features': features}




#train/test split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3)


clf = SVC(kernel='linear', C=1).fit(X_train,y_train)

@mex_framework.algorithm
def algorithm():
    algorithmID = 0
    algorithmName = 'Support Vector Machine'
    algorithmURI = 'http://scikit-learn.org/stable/modules/generated/sklearn.svm.SVC.html#sklearn.svm.SVC.predict'
    algorithmClass = 'SVM'
    return {'algorithmID' : algorithmID, 'algorithmName':algorithmName,
              'algorithmURI': algorithmURI,
              'algorithmClass': algorithmClass}


y_pred = clf.predict(X_test)
#accuracies = metrics.accuracy_score(y_test, y_pred)

clf.fit(X,y)

new_data = [[1,2,3,4],
            [4,3,2,1],
            [4,2,1,3],
            [3,1,4,2],
            [2,2,4,4],
            ]

predictions  = clf.predict(new_data) # 0 - Setosa, 1 - Versicolor, 2 - Virginica
print predictions

predicted = cross_val_predict(clf,X,y)
#accuracies = cross_val_score(clf, X, y)

@mex_framework.sampling_method
def set_sampling_method():
    klass = 'HoldOutCrossValidation'
    return {'class': klass, 'trainSize':X_train, 'testSize':X_test}

accuracies = metrics.accuracy_score(y, predicted)
print accuracies

@mex_framework.measure
def measure():
    return {'idMeasure' : 'ACCURACY','algorithmID': '0', 'idPhase': 'TEST'}


@mex_framework.execution
def execution():
   return {'id':"0", 'accuracy': accuracies}


@mex_framework.start
def start():
    return 'Ok'



set_features()
set_dataset_name()
set_experiment_info()
set_hardware_info()
set_sampling_method()
execution()
algorithm()
measure()
start()




