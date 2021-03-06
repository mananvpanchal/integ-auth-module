/**
 * Created by Admin on 10-02-2016.
 */

import React from 'react';
import { connect } from 'react-redux';

import createLoginAction from '../actions/login';

let Login = ({ cred, doLogin }) => {
	let userinp;
	let pswdinp;
	return (
		<div>
			<div><label for="username">Username</label><input id="username" type="text"
																																						ref={(node) => { userinp = node; }}/></div>
			<div><label for="password">Password</label><input id="password" type="password"
																																						ref={(node) => { pswdinp = node; }}/></div>
			<div>
				<button onClick={() => { doLogin(userinp.value, pswdinp.value); }}>Login</button>
			</div>
		</div>
	);
};

Login = connect(
	(state) => {
		return {
			loadingFlag: state.loadingFlag
		}
	},
	(dispatch) => {
		return {
			doLogin: (username, password) => {
				dispatch(createLoginAction(username, password));
			}
		}
	}
)(Login);

export default Login;