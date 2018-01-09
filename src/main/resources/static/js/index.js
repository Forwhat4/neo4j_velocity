
var ecConfig = echarts.config;
var myChart = null;
var option = null;


$(function () {
    // 基于准备好的dom，初始化echarts实例
    myChart = echarts.init(document.getElementById('graph'));

    //准备好数据
    //以下为节点数据，每一个{}里面为一个节点，category（该节点类别），name（关系连接的关键字，可以理解为键值中的键，可为数字）
    //value(节点的值，可以设置节点半径与该值的关系)，label（该字段是我用来显示该节点标签的，可以改名），大家也可以自己设置其他字段
   var graph = {};//数据
     graph.nodes = [
        {category:0,name: 1, value :5,label: '乔布斯'},
        {category:1, name: 2,value : 2,label: '丽萨-乔布斯'},
        {category:1, name: 3,value : 3,label: '保罗-乔布斯'},
        {category:1, name: 4,value : 3,label: '克拉拉-乔布斯'},
        {category:1, name: 5,value : 7,label: '劳伦-鲍威尔'},
        {category:2, name: 6,value : 5,label: '史蒂夫-沃兹尼艾克'},
        {category:2, name: 7,value : 8,label: '奥巴马'},
        {category:2, name: 8,value : 9,label: '比尔-盖茨'},
        {category:2, name: 9,value : 4,label: '乔纳森-艾夫'},
        {category:2, name: 10,value : 4,label: '蒂姆-库克'},
        {category:2, name: 11,value : 1,label: '龙-韦恩'},
    ];
    //以下为连线关系数据，每一个{}里面为一个关系，source（起点，对应上面的name），target（终点，对应上面的name）
    //value(起点到终点的距离，值越大，权重越大，距离越短)
    graph.links = [
        {source : 2, target : 1, value : 5, label: '女儿'},
        {source : 3, target : 1, value : 2, label: '父亲'},
        {source : 4, target : 1, value : 1, label: '母亲'},
        {source : 5, target : 1, value : 2, label: '不详'},
        {source : 6, target : 1, value : 3, label: '合伙人'},
        {source : 7, target : 1, value : 1, label: '不详'},
        {source : 8, target : 1, value : 6, label: '竞争对手'},
        {source : 9, target : 1, value : 1, label: '爱将'},
        {source : 10, target : 1, value : 1, label: '不详'},
        {source : 11, target : 1, value : 1, label: '不详'},
        {source : 4, target : 3, value : 1, label: '不详'},
        {source : 7, target : 3, value : 1, label: '不详'},
        {source : 7, target : 4, value : 1, label: '不详'},
        {source : 7, target : 5, value : 1, label: '不详'},
        {source : 7, target : 6, value : 1, label: '不详'},
        {source : 8, target : 7, value : 6, label: '不详'},
        {source : 8, target : 4, value : 1, label: '不详'},
        {source : 10, target : 7, value : 1, label: '不详'}
    ];
    //categories为node节点分类，categoriesshort为显示图例，后者比前者短，可以使得图例中没有主干人物
    graph.categories = [{name:'主干人物'},{name:'家人'},{name:'朋友'} ];
    graph.categoriesshort = [{name:'家人'},{name:'朋友'} ];

    // 设置关系图节点标记的大小
    graph.nodes.forEach(function (node) {
        node.symbolSize = node.value*8;
    });
    option = {
        title: {
            text: '人际关系网络图',//标题
            //subtext: '人物关系：乔布斯',//标题副标题
            top: 'top',//相对在y轴上的位置
            left: 'center'//相对在x轴上的位置
        },
        tooltip : {//提示框，鼠标悬浮交互时的信息提示
            trigger: 'item',//数据触发类型
            formatter: function(params){//触发之后返回的参数，这个函数是关键
                //console.log(params);
                if (params.data.category !=undefined) {//如果触发节点
                    return '人物:'+params.data.label;//返回标签
                }else {//如果触发边
                    return '关系:'+params.data.label;
                }
            },
        },
        //工具箱，每个图表最多仅有一个工具箱
        toolbox: {
            show : true,
            feature : {//启用功能
                //dataView数据视图，打开数据视图，可设置更多属性,readOnly 默认数据视图为只读(即值为true)，可指定readOnly为false打开编辑功能
                dataView: {show: true, readOnly: true},
                restore : {show: true},//restore，还原，复位原始图表
                saveAsImage : {show: true}//saveAsImage，保存图片
            }
        },
        //全局颜色，图例、节点、边的颜色都是从这里取，按照之前划分的种类依序选取
        color:['rgb(194,53,49)','rgb(178,144,137)','rgb(97,160,168)'],
        //图例，每个图表最多仅有一个图例
        legend: [{
            x: 'left',//图例位置
            //图例的名称，这里返回短名称，即不包含第一个，当然你也可以包含第一个，这样就可以在图例中选择主干人物
            data: graph.categoriesshort.map(function (a) {
                return a.name;
            })
        }],
        //sereis的数据: 用于设置图表数据之用
        series : [
            {
                name: '人际关系网络图',//系列名称
                type: 'graph',//图表类型
                layout: 'force',//echarts3的变化，force是力向图，circular是和弦图
                draggable: true,//指示节点是否可以拖动
                data: graph.nodes,//节点数据
                links: graph.links,//边、联系数据
                categories: graph.categories,//节点种类
                focusNodeAdjacency:true,//当鼠标移动到节点上，突出显示节点以及节点的边和邻接节点
                roam: true,//是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移，可以设置成 'scale' 或者 'move'。设置成 true 为都开启
                label: {//图形上的文本标签，可用于说明图形的一些数据信息
                    normal: {
                        show : true,//显示
                        position: 'inside',//相对于节点标签的位置
                        textStyle : { //标签的字体样式
                            color : '#cde6c7', //字体颜色
                            fontStyle : 'normal',//文字字体的风格 'normal'标准 'italic'斜体 'oblique' 倾斜
                            fontWeight : 'bolder',//'normal'标准'bold'粗的'bolder'更粗的'lighter'更细的或100 | 200 | 300 | 400...
                            fontFamily : 'sans-serif', //文字的字体系列
                            fontSize : 12, //字体大小
                        },
                        //回调函数，你期望节点标签上显示什么
                        formatter: function(params){
                            return params.data.label;
                        },
                    }
                },
                //节点的style
                itemStyle:{
                    normal:{
                        opacity:1,//设置透明度为0.8，为0时不绘制
                    },
                },
                // 关系边的公用线条样式
                lineStyle: {
                    normal: {
                        show : true,
                        color: 'source',//决定边的颜色是与起点相同还是与终点相同
                        curveness: 0.3//边的曲度，支持从 0 到 1 的值，值越大曲度越大。
                    }
                },
                force: {
                    edgeLength: [100,200],//线的长度，这个距离也会受 repulsion，支持设置成数组表达边长的范围
                    repulsion: 500//节点之间的斥力因子。值越大则斥力越大
                }
            }
        ]
    };
    console.info("init");
    console.info(option.series[0]);
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});


function loadTopo(name) {
    myChart.clear();
    $.ajax({
        type : "POST",
        url : "/rel",
        dataType : "json",
        data : {
            "name" : name,
        },
        async : false,
        success : function (data) {
            //console.info(data);
            option.series[0].data=data.nodelist;
            option.series[0].links=data.linklist;
            option.series[0].categories=[{name:'朋友'},{name:'核心'}];
            console.info("callback");
            console.info(option.series[0]);
            myChart.setOption(option);
        }
    });
}

$("#btn").click(function () {
   loadTopo($("#name").val());
});