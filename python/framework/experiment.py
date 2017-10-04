from sklearn.datasets import load_iris
from sklearn.neighbors import KNeighborsClassifier
from sklearn.cross_validation import train_test_split
from sklearn import metrics
import mex_framework


iris = load_iris()

X = iris.data
y = iris.target

#train/test split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3)


knn = KNeighborsClassifier(n_neighbors=1)

knn.fit(X_train, y_train)
y_pred = knn.predict(X_test)
accuracy = metrics.accuracy_score(y_test, y_pred)

knn.fit(X,y)

new_data = [[1,2,3,4],
            [4,3,2,1],
            [4,2,1,3],
            [3,1,4,2],
            [2,2,4,4],
            ]

predictions  = knn.predict(new_data) # 0 - Setosa, 1 - Versicolor, 2 - Virginica
#print predictions

@mex_framework.experiment_info
def set_experiment_info():
    params = {'author': "Igor Costa 2",
              'email': "igorcosta@ime.eb.br",
              'title': "Scikit Learn Test",
              'tags': ['Scikit Learn', 'Python 2.7', 'MEX', 'Iris']}
    return params

@mex_framework.execution
def execution():
    params = {'id':"0",
              'accuracy': accuracy}
    return params

@mex_framework.hardware
def set_hardware_info():
    params = {'cpu': "Intel Core i7",
              'memory': "16 GB",
              'hd': "SSD",
              'cache': '6M',
              ' os': 'macOS Sierra 10.12',
              'video': 'Nvidia GeForce GT750M'}
    return params

@mex_framework.dataset_name
def set_dataset_name():
    params = {'name': 'Iris',
              'URI': 'http://archive.ics.uci.edu/ml/datasets/Iris',
              'description': 'This is perhaps the best known database to be found in the pattern recognition literature. Fisher\'s paper is a classic in the field and is referenced frequently to this day. (See Duda & Hart, for example.) The data set contains 3 classes of 50 instances each, where each class refers to a type of iris plant. One class is linearly separable from the other 2; the latter are NOT linearly separable from each other.' }
    return params


@mex_framework.features
def set_features():
    features = iris.feature_names
    params = {'features': features}
    return params

@mex_framework.algorithm
def algorithm():
    params = {'algorithmID' : '0',
              'algorithmName':'K-Nearest Neighbors',
              'algorithmURI': 'http://scikit-learn.org/stable/modules/neighbors.html',
              'algorithmClass': 'NearestNeighbor'}
    return params

@mex_framework.measure
def measure():
    params = {'idMeasure' : 'ACCURACY',
              'algorithmID': '0',
              'idPhase': 'TEST'}
    return params

@mex_framework.sampling_method
def set_sampling_method():
    params = {'trainSize':X_train,
              'testSize':X_test}
    return params

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




