/**
 * Created by Admin on 17-02-2016.
 */

export default ({ getState, dispatch }) => {
	return (next) => {
		return (action) => {
			console.log(next);
			const returnValue = next(action);
			console.log(returnValue);
		};
	};
};