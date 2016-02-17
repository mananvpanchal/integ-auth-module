import React from 'react';
import ReactDOM from 'react-dom';
import Login from './components/login';
import '../css/test.css';
import { Provider } from 'react-redux';
import store from './store';

ReactDOM.render(<Provider store={store}><Login /></Provider>, document.getElementById('app'));