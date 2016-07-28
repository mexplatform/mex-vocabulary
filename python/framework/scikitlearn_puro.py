from sklearn import tree
from sklearn.datasets import load_iris
from sklearn.cross_validation import cross_val_score
import mex_framework

iris = load_iris()
clf = tree.DecisionTreeClassifier()
clf = clf.fit(iris.data, iris.target)

feature_names = iris.feature_names
class_names = iris.target_names

print feature_names, class_names

predicition = clf.predict_proba(iris.data[:1, :])
print predicition