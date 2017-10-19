# Kotlin-UIKit
使用iOS的编程思路来写安卓的代码

这个库在Activity和View的基础上，按照iOS的逻辑封装的一套库，目的是希望能够用写iOS代码的方式来写安卓的代码。

下面是一段代码及对应的显示效果

		//蓝色视图，上面放置一个黄色的视图
  		blueview = UIView()
        blueview!!.frame = CGRect(x = 100f, y = 100f, width = 130f, height = 140f)
        this.view!!.addSubview(blueview!!)
        blueview!!.backgroundColor = Color.BLUE

        yellowview = UIView()
        yellowview.frame = CGRect(10f,10f,40f,40f)
        yellowview.backgroundColor = Color.YELLOW
        blueview!!.addSubview(yellowview)

		 //带有点击事件的视图，点击后由ViewController控制其他视图动作
        touchView = DemoView()
        touchView.frame = CGRect(110f,400f,100f,100f)
        touchView.backgroundColor = Color.RED
        touchView.layer.borderColor = Color.YELLOW
        touchView.layer.cornerRadius = 20f
        touchView.layer.borderWidth = 6f
        this.view!!.addSubview(touchView)


        touchView.touchblock = {

            moveBlueView()
        }

		 //文本
        var label = UILabel()
        label.frame = CGRect(10f,250f,700f,50f)
        label.backgroundColor = Color.GREEN
        label.text = "Hello UIKit！"
        view!!.addSubview(label)


![](./screenshot/screenshot01.png)


[toc]


## 编码尺寸和dpi尺寸

因为安卓手机的屏幕尺寸和分辨率不一样的太多，这导致了我们在编码的时候写的尺寸在镇机上的显示效果都不理想。这里我们采用比例换算的方式来解决这个问题：
在编码的时候，统一使用320的宽度来进行计算，至于这时候的屏幕高度是多少，这个就无法定死了。不同的分辨率的机型拿实际像素宽度除以320得到一个比例，这个比例我们就在整个项目中对所有的视图进行应用。

编码尺寸是指在写代码的时候，我们给视图赋值的尺寸，以屏幕宽度320作为标准
dpi尺寸，指的是视图在实际像素上应该具有的尺寸。

## 触摸事件

添加了touch事件，模仿iOS，实现了

* touchesBegan
* touchesMove
* touchesEnd

能力有限，多点触摸搞不下去了。在ACTION_MOVE事件中，无法获取到究竟是哪一个触摸点在移动，所以只能暂时实现单点触摸了。

另外注意判断移动距离时还得使用Android原有的判断方式，因为UITouch判断存在问题，会出现视图抖动的现象。

示例代码：

	var lastX = 0f
    var lastY = 0f


    override fun touchesBegan(touches: MutableList<UITouch>, withEvent: MotionEvent) {


        val atouch = touches.first()
        NSLog.print("touchesBegan $name :${atouch.touchX},y:${atouch.touchY}")


        //注意判断移动距离时还得使用Android原有的判断方式，因为UITouch判断存在问题，会出现视图抖动的现象。
        lastX = withEvent.getX(withEvent.actionIndex)
        lastY = withEvent.getY(withEvent.actionIndex)
    }

    override fun touchesMoved(touches: MutableList<UITouch>, withEvent: MotionEvent) : Boolean {


        //开始移动
        var currentx = withEvent.getX(withEvent.actionIndex)
        var currenty = withEvent.getY(withEvent.actionIndex)

        NSLog.print("touchesMoved $name :{$currentx,$currenty}  last:{$lastX,$lastY}")
        //注意获取到的是dpi的位置，需要转换成320比例的
        frame.x += (currentx - lastX)/frame.frameRatio
        frame.y += (currenty - lastY)/frame.frameRatio

        lastX = currentx
        lastY = currenty

        //改变了尺寸之后要调用setNeedsDisplay，否则不会触发重新绘制视图的动作。
        setNeedsDisplay()

        return true

    }

    override fun touchesEnd(touches: MutableList<UITouch>, withEvent: MotionEvent): Boolean {
        val atouch = touches.first()

        NSLog.print("touchesEnd $name :${atouch.touchX},y:${atouch.touchY}")
        return true

    }
    

![](./screenshot/touchdemo.gif)
