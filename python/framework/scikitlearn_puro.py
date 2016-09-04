import numpy as np
from sklearn import cross_validation
#from sklearn import datasets
from sklearn import svm

#iris = datasets.load_iris()
#iris.data.shape, iris.target.shape

#clf = tree.DecisionTreeClassifier()
#clf = clf.fit(iris.data, iris.target)

#feature_names = iris.feature_names
#class_names = iris.target_names

#print feature_names, class_names

#predicition = clf.predict_proba(iris.data[:1, :])
#print predicition

#-----------------------------------------------------------------------------#

from sklearn.datasets import load_iris

#dataset
iris = load_iris()

#features
#print iris.feature_names
#print iris.data

#target
#print iris.target
# print iris.target_names

#print type(iris.data)
#print type(iris.target)

#print iris.target.shape

X = iris.data # X is uppercase because it's representing a matrix
y = iris.target # y is lowercase because it' representing a vector
