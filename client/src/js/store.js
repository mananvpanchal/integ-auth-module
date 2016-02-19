/**
 * Created by Admin on 17-02-2016.
 */

import { createStore, combineReducers } from 'redux';
import { applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';

import { axiosMiddleware } from './middlewares/middlewares'
import reducers from './reducers';

let createStoreWithMiddleware = applyMiddleware(axiosMiddleware, thunkMiddleware)(createStore);

let finalReducer = combineReducers(reducers);

export default createStoreWithMiddleware(finalReducer);