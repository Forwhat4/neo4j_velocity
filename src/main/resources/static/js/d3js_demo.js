var nodes = [ { name: "刘一"    }, { name: "陈二" },
    { name: "张三"    }, { name: "李四"   },
    { name: "王五"   }, { name: "赵六"    },
    { name: "田七"    } ];

var edges = [  { source : 0  , target: 1 , type : "朋友"} , { source : 0  , target: 2 , type : "朋友"} ,
    { source : 0  , target: 3 , type : "朋友"} , { source : 1  , target: 4 , type : "朋友"} ,
    { source : 1  , target: 5 , type : "朋友"} , { source : 1  , target: 6 , type : "朋友"} ,
    { source : 1  , target: 0 , type : "朋友"} ];

var width = 400;
var height = 400;


var svg = d3.select("body")
    .append("svg")
    .attr("width",width)
    .attr("height",height);


var force = d3.layout.force()
    .nodes(nodes)		//指定节点数组
    .links(edges)		//指定连线数组
    .size([width,height])	//指定范围
    .linkDistance(150)	//指定连线长度
    .charge(-400);	//相互之间的作用力

force.start();	//开始作用

//绘制箭头
svg.append("defs").selectAll("marker")
    .data(["end"])
    .enter()
    .append("svg:marker")
    .attr("id","arrow")   //连线可以根据id添加标识
    //.attr('class','arrow')
    //.attr("viewBox", "0 -5 10 10")   	//坐标系的区域
    .attr("viewBox", "0 0 12 12")
    .attr("refX", 20)  //标识坐标
    .attr("refY", 6)
    .attr("markerWidth", 20)    //标识大小
    .attr("markerHeight", 16)
    .attr("markerUnits","userSpaceOnUse")   //标识大小的基准，有两个值：strokeWidth（线的宽度）和userSpaceOnUse（图形最前端的大小）
    .attr("orient", "auto")  //绘制方向，可设定为：auto（自动确认方向）和 角度值
    .append("svg:path")
    //.attr("d", "M0,-5L10,0L0,5")  //标识的路径
    .attr("d", "M2,2 L10,6 L2,10 L6,6 L2,2")
    .attr('fill','green'); //填充颜色



//添加连线
var svg_edges = svg.selectAll("line")
    .data(edges)
    .enter()
    .append("line")
    .attr("class", "link")
    .attr('stroke-width',1)  //线条粗细
    .attr("marker-end","url(#arrow)") //添加箭头  marker-start ：路径起点处  marker-mid：路径中间端点处（必须是 path 中间出现的点）  marker-end ：路径终点处
    .attr('stroke','#999');   //描边颜色


var color = d3.scale.category20();

//节点拖拽、固定
var drag = force.drag()
    .on("dragstart",function(d,i){
        d.fixed = true;    //拖拽开始后设定被拖拽对象为固定
        //label_text_2.text("拖拽状态：开始");
    })

//添加节点
var svg_nodes = svg.selectAll("circle")
    .data(nodes)
    .enter()
    .append("circle")
    .attr("r",10)
    .style("fill",function(d,i){
        return color(i);
    })
    .call(drag);	//使得节点能够拖动

//添加描述节点的文字
var svg_texts = svg.selectAll("text")
    .data(nodes)
    .enter()
    .append("text")
    .style("fill", "black")
    .attr("dx", -15)
    .attr("dy", 30)
    .text(function(d){
        return d.name;
    });

//添加描述连线的文字
var edges_text = svg.selectAll(".linetext")
    .data(edges)
    .enter()
    .append("text")
    .attr("class","linetext")
    .text(function(d){
        return d.type;
    });


force.on("tick", function(){	//对于每一个时间间隔

    //更新连线坐标
    svg_edges.attr("x1",function(d){ return d.source.x; })
        .attr("y1",function(d){ return d.source.y; })
        .attr("x2",function(d){ return d.target.x; })
        .attr("y2",function(d){ return d.target.y; });

    //更新节点坐标
    svg_nodes.attr("cx",function(d){ return d.x; })
        .attr("cy",function(d){ return d.y; });

    //更新文字坐标
    svg_texts.attr("x", function(d){ return d.x; })
        .attr("y", function(d){ return d.y; });

    //更新连接线上文字的位置
    edges_text.attr("x",function(d){ return (d.source.x + d.target.x) / 2 ; });
    edges_text.attr("y",function(d){ return (d.source.y + d.target.y) / 2 ; });
});

