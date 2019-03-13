<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>江南布衣运维服务</title>
    <link href="./css/bootstrap.css" rel="stylesheet" />
    <link href="./css/base.css" rel="stylesheet" />
</head>
<body style="min-height:800px; height: auto">
    

        <nav class="navbar navbar-default row no-yj  navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                <!--            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-list-left" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>  -->
                    <a class="navbar-brand" href="main.do">Q&A库管理系统</a>
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
                                <li><a href="toFind.do">查找词条</a></li>
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
                   <div class="col-md-6">
                    <div class="panel panel-default ">
                        <div class="panel-body">
                            <form action="find.do">
                               
                                <div class="form-group">
                                    <label for="exampleInputPassword1">词条关键词</label>
                                    <input type="text" class="form-control" id="question" placeholder="在这里输入要查询的词条大概关键词 (必填)" name="question" required="required"/>
                                </div>
                                 <div class="form-group">
                                    <label for="exampleInputEmail1">词条大概回复</label>
                                    <input type="text" class="form-control" id="answer" placeholder="在这里输入词条的大概回复 (非必填)" name="answer"  />
                                </div>

                                <div class="checkbox">
                                </div>
                                <button type="submit" class="btn btn-default">查找</button>
                            </form>
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
