import numpy as np
from sklearn import tree
from sklearn.datasets import load_iris
from sklearn import cross_validation
from sklearn import datasets
from sklearn import svm
import mex_framework



iris = datasets.load_iris()
#clf = tree.DecisionTreeClassifier()
#clf = clf.fit(iris.data, iris.target)
#data_shape = iris.data.shape
#data_target = iris.target.shape((150, 4), (150,))

@mex_framework.experiment_info
def set_experiment_info():
    created_by = "Igor Costa 4"
    email="igorcosta@ime.eb.br"
    title="Scikit Learn Test"
    tags=['Scikit Learn', 'Python 2.7', 'MEX', 'Iris']

    params = {
        'author': created_by,
        'email': email,
        'title': title,
        'tags': tags
    }

    return params

@mex_framework.hardware
def set_hardware_info():
    cpu="Intel Core i7"
    memory="8 GB"
    hdType="SSD"
    cache = '6M'
    os = 'OSX El Capitan 10.11.5'
    video = 'Nvidia GForce 630M'


    params = {
        'cpu': cpu,
        'memory': memory,
        'hd': hdType,
        'cache': cache,
        'os': os,
        'video': video
    }

    return params


@mex_framework.sampling_method
def set_sampling_method():
    klass = "CrossValidation"
    train_size =0.7
    test_size = 0.3
    folds = 10
    sequential = True


    params = {
        'klass': klass,
        'trainSize': train_size,
        'testSize': test_size,
        'folds': folds,
        'sequential': sequential
    }

    return params

'''def train():
    X_train, X_test, y_train, y_test = cross_validation.train_test_split(iris.data, iris.target, test_size = 0.7, random_state = 0)

    X_train.shape
    y_train.shape((90, 4), (90,))

    X_test.shape
    y_test.shape((60, 4), (60,))

    clf = svm.SVC(kernel='linear', C=1)
    scores = cross_validation.cross_val_score(clf, iris.data, iris.target, cv = 5)
    print("Accuracy: %0.2f (+/- %0.2f)" % (scores.mean(), scores.std() * 2))

@mex_framework.accuracy
def predict():
    _predict = cross_validation.cross_val_predict(clf, iris.data,iris.target, cv=10)
    metrics.accuracy_score(iris.target, _predict)

#def start():
    feature_names = iris.feature_names
    class_names = iris.target_names

    #print feature_names, class_names

#def make_prediction():
    predicition = clf.predict(iris.data[:1, :])
    #print predicition

train()'''

#set_sampling_method()
set_experiment_info()
#set_hardware_info()
