from sklearn import tree
from sklearn.datasets import load_iris
from sklearn.cross_validation import cross_val_score
import mex_framework

class ScikitLearnExample001(object):

    iris = load_iris()
    clf = tree.DecisionTreeClassifier()
    clf = clf.fit(iris.data, iris.target)

    @mex_framework.experiment_info
    def set_experiment_info(self):
        created_by="Igor Costa"
        email="igorcosta@ime.eb.br"
        title="Scikit Learn Test"
        tags=["WEKA", "J48", "DecisionTable", "MEX", "Iris"]

        params = {
            'createdBy' : created_by,
            'email' : email,
            'title' : title,
            'tags' : tags,
        }

        return params

    @Hardware
    def set_hardware_info(self):
        cpu="Intel Core i7"
        memory="8 GB"
        hdType="SSD"

        params = {
            'cpu': cpu,
            'memory': memory,
            'hdType': hdType,
        }

        return params

    def start(self):
        feature_names = self.iris.feature_names
        class_names = self.iris.target_names

        #print feature_names, class_names

    def make_prediction(self):
        predicition = self.clf.predict(self.iris.data[:1, :])
        #print predicition






def main():
    experiment = ScikitLearnExample001()
    experiment.set_experiment_info()
    experiment.make_prediction()

main()
