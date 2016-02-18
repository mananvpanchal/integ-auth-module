/**
 * Created by Admin on 17-02-2016.
 */

function f(x) {
	return 2*x + 4;
}

function g(x) {
	return x*x;
}

function compose(f, g) {
	return function(x) {
		return f(g(x));
	}
}

var composed = compose(f, g);

console.log(composed(2));