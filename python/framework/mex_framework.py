# -*- coding: UTF-8 -*-

import requests
import json

def algorithm(set_algorithm):
    def get_algorithm(*args, **kwargs):
        params = set_algorithm()
        json_params = json.dumps(params, indent=2)
        print json_params
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/algorithm',json = params)
        print response
        return set_algorithm(*args, **kwargs)
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
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/sethardware',json = params)
        print response
        return set_hardware_info(*args, **kwargs)
    return get_hardware_info

def sampling_method(set_sampling_method):
    def get_sampling_method(*args, **kwargs):
        params = set_sampling_method()
        json_params = json.dumps(params, indent=2)
        print json_params
        response =requests.post('http://localhost:8080/rest4mex/resources/experiment/setsamplingmethod', json=params)
        print response
        return set_sampling_method(*args, **kwargs)
    return get_sampling_method

def interface_version(set_interface_version):
    def get_interface_version(*args, **kwargs):
        params = set_interface_version()
        json_params = json.dumps(params, indent=2)
        print json_params
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/setinterfaceversion', json=params)
        print response
        return set_interface_version(*args, **kwargs)
    return get_interface_version

def measure(set_measure):
    def get_measure(*args, **kwargs):
        params = set_measure()
        json_params = json.dumps(params, indent=2)
        print json_params
        response = requests.post('http://localhost:8080/rest4mex/resources/experiment/measure', json=params)
        print response
        return set_measure(*args, **kwargs)
    return get_measure