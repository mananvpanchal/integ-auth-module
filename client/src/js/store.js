/**
 * Created by Admin on 17-02-2016.
 */

import { createStore, combineReducers } from 'redux';
import { applyMiddleware } from 'redux';
import cred from './reducers/login';
import testMiddleware from './middlewares/test';
import testMiddleware2 from './middlewares/test2';
import thunkMiddleware from 'redux-thunk';

let createStoreWithMiddleware = applyMiddleware(thunkMiddleware, testMiddleware, testMiddleware2)(createStore);

//let finalReducer = combineReducers({ cred });

export default createStoreWithMiddleware(cred);

//export default createStore(cred);