/*eslint-disable */
var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./webpack/webpack.config');

new WebpackDevServer(webpack(config), {
  publicPath: config.output.publicPath,
  //hot: true,
  inLine: true,
  historyApiFallback: true,
  contentBase:'./static',
  stats: {
    colors: true
  }
}).listen(3000, 'localhost', function (err, result) {
  if (err) {
    console.log(err);
  }

  console.log('Listening at localhost:3000');
});
