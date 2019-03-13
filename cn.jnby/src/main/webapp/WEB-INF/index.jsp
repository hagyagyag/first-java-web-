<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>江南布衣运维服务</title>
    <link href="./css/bootstrap.css" rel="stylesheet" />
    <link href="./css/base.css" rel="stylesheet" />
    
		<style type="text/css">
			p {
				width: 300px;
				/*设置文本框大小*/
				white-space: nowrap;
				/*设置内容不换行*/
				text-overflow: ellipsis;
				/*设置文字超出文本框的内容显示成...*/
				overflow: hidden;
				/*超出部分隐藏*/
			}
		</style>

</head>
<body style="min-height:800px; height: auto">
    

        <nav class="navbar navbar-default row no-yj  navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                     <!--      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-list-left" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>  -->
                    <a class="navbar-brand" href="toDel.do">Q&A库管理系统</a>
                </div>

    

            </div>
        </nav>
        
    
        <div class="row" style="margin-top:70px">
            <div class="  nav-left col-md-2 no-padding" id="nav-list-left">
                <div class="user-panel">
                    <img src="img/user/user.jpg" class="img-circle center-block" />
                </div>

                <div class="nav-list" >
                    <ul>
                        <li>
                            <a href="main.do">首页</a>

                        </li>


                        <li class="nav-left-dropdown">
                            <a href="#">添加词条</a>
                            <ul class="nav-left-dropdown-menu">
                                <li><a href="toUpdate.do">词条</a></li>
                            </ul>
                        </li>
                        <li class="nav-left-dropdown">
                            <a href="#">查找词条</a>
                            <ul class="nav-left-dropdown-menu">
                                <li><a href="toFind.do" >查找词条</a></li>
                                <li><a href="class.do?classify=伯俊">伯俊</a></li>
                                <li><a href="class.do?classify=打印机">打印机</a></li>
                                <li><a href="class.do?classify=内淘">内淘</a></li>
                                <li><a href="class.do?classify=CRM会员问题">CRM会员问题</a></li>
                                <li><a href="class.do?classify=OA问题">OA问题</a></li>
                                <li><a href="class.do?classify=HR问题">HR问题</a></li>
                                <li><a href="class.do?classify=其它">其它</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>

<div class="main-right  col-md-10 col-md-offset-2">
                <div class="col-md-12">
                    <div class="panel panel-default ">
                        <div class="panel-heading">
                            
                        </div>
                        <div class="panel-body">
                            <table class="table table-bordered tb-hover">
                                <thead>
                                    <tr>
                                        <th>类别</th>
                                        <th>关键字</th>
                                        <th>回答</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                	<!-- 表格数据的Java代码 -->
                                <tbody>
       						<c:forEach items="${find}" var="f" varStatus="s">
							<tr>
								<td>${f.classify}</td>
								<td>
								  <div>
								  <p 
								  href=""
								  onclick="return confirm('${f.question}');"
								  >${f.question}</p>
								  </div>
								</td>
								<td>
								 <div >
								   <p>${f.answer}</p>
								 </div>
								</td>
								<td><a 
									href=""
									onclick="return confirm('${f.answer}');"
									>查看详情</a>
									<a 
									href="download.do?filename=${f.url}"
									>${f.url}</a>
							<!--  	   <a
									href="del.do?id=${f.id}"
									onclick="return confirm('确定删除${f.question}吗?');">删除</a>
									</td>
						    -->
						    </c:forEach>

                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>
               
          </div>
     </div>
</body>
    <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $(".dropdownMenu").dropdown();
            $(".nav-left-dropdown>a").click(function () {
                if ($(this).attr("d") != 1 && $(this).attr("d") != undefined) {
                    $(this).removeClass("nav-left-dropdown-a");
                    $this = $(this);
                    $(this).next("ul").slideUp(300, function () { $this.removeClass("nav-left-bottrom-border"); $this.removeClass("nav-left-dropdown-ul") });
                    $(this).attr("d", "1");
                } else {
                    $(this).addClass("nav-left-dropdown-a");
                    $(this).addClass("nav-left-bottrom-border");
                    $(this).next("ul").addClass("nav-left-dropdown-ul").slideDown(300);
                    $(this).attr("d", "2");
                }
            })
        });
    </script>
</html>
