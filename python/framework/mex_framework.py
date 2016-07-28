# -*- coding: UTF-8 -*-


import requests
import json

def algorithm(_algorithm):
    def get_algorithm(*args, **kwargs):
        params = _algorithm()
        json_params = json.dumps(params, indent=2)
        print json_params
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/algorithm',json = params)
        print response
        return algorithm(*args, **kwargs)
    return get_algorithm

def dataset_name(set_dataset_name):
    def get_dataset_name(*args, **kwargs):
        params = set_dataset_name()
        json_params = json.dumps(params, indent=2)
        print json_params
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/setdatasetname',json = params)
        print response
        return set_dataset_name(*args, **kwargs)
    return get_dataset_name

def experiment_info(set_experiment_info):
    def get_experiment_info(*args, **kwargs):
        params = set_experiment_info()
        json_params = json.dumps(params, indent=2)
        print json_params
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/setexperimentinfo',json = params)
        print response
        return set_experiment_info(*args, **kwargs)
    return get_experiment_info

def features(set_features):
    def get_features(*args, **kwargs):
        params = set_features()
        json_params = json.dumps(params, indent=2)
        print json_params
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/setfeatures',json = params)
        print response
        return set_features(*args, **kwargs)
    return get_features

def hardware(set_hardware_info):
    def get_hardware_info(*args, **kwargs):
        params = set_hardware_info()
        json_params = json.dumps(params, indent=2)
        print json_params
        requests.post('http://localhost:8080/rest4mex/resources/experiment/sethardware',json = params)
        return set_hardware_info(*args, **kwargs)
    return get_hardware_info

def sampling_method(set_sampling_method):
    def sampling_method(*args, **kwargs):
        params = set_sampling_method()
        json_params = json.dumps(params, indent=2)
        print json_params
        requests.post('http://localhost:8080/rest4mex/resources/experiment/setsamplingmethod', json=params)
        return set_sampling_method(*args, **kwargs)
    return sampling_method