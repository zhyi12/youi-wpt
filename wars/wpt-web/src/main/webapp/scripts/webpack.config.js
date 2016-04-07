module.exports = {
    entry: {
    	'youi.mobile':["./3.0/youi-phone/youi.pageview.js",
    	               "./3.0/youi-phone/youi.page.js",
    	               "./3.0/youi-phone/youi.list.js",
    	               "./3.0/youi-phone/youi.tabs.js",
    	               "./3.0/youi-phone/youi.button.js",
    	               "./3.0/youi-phone/youi.form.js"],
    	'app':'./app.js',
    	'page.index':'./pages/page.index.js',
    	'page.welcome':'./pages/page.welcome.js',
    	'page.config':'./pages/page.config.js',
    	'page.msg':'./pages/page.msg.js',
    	'page.tel':'./pages/page.tel.js'
    },
    output: {
        path: __dirname+'/3.0/lib',
        filename: "[name].min.js"
    },
    module: {
        loaders: [
            { test: /\.css$/,exclude: /(node_modules|bower_components)/, loader: "style!css" },
            { test: /\.jsx?$/,exclude: /(node_modules|bower_components)/,loader: 'jsx-loader?harmony'},
            { test: /\.less$/, exclude: /(node_modules|bower_components)/,loader: "style!css!less" }
        ]
    }
};