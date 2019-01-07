
function Point(x, y, g) {
    this.x = x || 0;
    this.y = y || 0;
    this.graphic = g;
    this.main_line = null;
};

Point.prototype.x = null;
Point.prototype.y = null;
Point.prototype.graphic = null;
Point.prototype.main_line = null;

window.onload = function () {
    Raphael.fn.line = function (startX, startY, endX, endY) {
        return this.path('M' + startX + ' ' + startY + ' L' + endX + ' ' + endY);
    };

    var paper = Raphael("canvas", 500, 400);
    var clearbtn = $('#clearbtn');
    var randombtn = $('#randombtn');
    var runbtn = $('#runbtn');
    var points = [];
    var canvas = null;
    var locked = false;
    var convexH = null;
    var auxtimer = null;

    function lineAnim(p, q) {
        line = paper.line(p.x, p.y, p.x, p.y).attr({
            'stroke-linecap': 'round',
            'stroke-linejoin': 'round',
            'stroke': '#23cec5'
        });
        p.main_line = line.animate({
            'stroke-width': '4',
            'path': 'M' + p.x + ' ' + p.y + ' L' + q.x + ' ' + q.y
        }, 100);
    }

    function deleteLineAnim(p, q) {
        p.main_line.animate({
            'stroke-width': '0'
        }, 100);
    }

    function addPointToList(p){
      var node = document.createElement("li");
      var textnode = document.createTextNode("("+p.x + "," +p.y+")");
      node.appendChild(textnode);
      document.getElementById("PointsInCH").appendChild(node);
    }

    function removePointFromList(){
      $('li', ul).last().remove()
    }



    var auxF = {
        lineAnim: lineAnim,
        deleteLineAnim: deleteLineAnim,
        addPointToList: addPointToList,
    };

    function getMousePos(e) {
        var totalOffsetX = 0;
        var totalOffsetY = 0;
        var canvasX = 0;
        var canvasY = 0;
        var currentElement = document.getElementById('canvas');

        do {
            totalOffsetX += currentElement.offsetLeft - currentElement.scrollLeft;
            totalOffsetY += currentElement.offsetTop - currentElement.scrollTop;
        }
        while (currentElement = currentElement.offsetParent);

        canvasX = e.pageX - totalOffsetX - document.body.scrollLeft;
        canvasY = e.pageY - totalOffsetY - document.body.scrollTop;

        return new Point(canvasX, canvasY, null, null);
    }

    function addPointAnim(p) {
        if (locked) {
            return;
        }
        c = paper.circle(p.x, p.y, 1).animate({
            r: 3,
            fill: '#131723',
            "stroke-width": 0
        }, 200);
        p.graphic = c;
        points.push(p);
    }

    function clear() {
        paper.clear();
        canvas = paper.rect(0, 0, 500, 400, 40).attr({
            fill: '#62a8ba',
            stroke: "none"
        });
        points = [];
        unlock();
        running = false;
        convexH = null;
        runbtn.text('Iniciar');
        runbtn.attr('disabled', false);
        $("#PointsInCH").empty();
        canvas.mouseup(function (e) {
            p = getMousePos(e);
            addPointAnim(p);
        });
    }

    function lock() {
        locked = true;
        randombtn.attr('disabled', true);
        if (convexH == null) {
            convexH = new CHAlgorith(points, auxF);
        }
    }

    function unlock() {
        locked = false;
        randombtn.attr('disabled', false);
    }

    function StartPause() {
        if (running) {
            running = false;
            window.clearInterval(auxtimer);
            runbtn.text('Continuar');
            runbtn.attr('disabled', false);
            clearbtn.attr('disabled', false);
        } else {
            running = true;
            lock();
            if (convexH == null) {
                convexH = new CHAlgorith(points, auxF);
            }
            runbtn.text('Pausa');
            clearbtn.attr('disabled', true);
            auxtimer = window.setInterval(function () {
                r = convexH.iterate();
                if (!r) {
                    window.clearInterval(auxtimer);
                    runbtn.text('Terminado');
                    runbtn.attr('disabled', true);
                    clearbtn.attr('disabled', false);
                }
            }, 700);
        }
    }

    clearbtn.click(function () {
        clear();
    });

    runbtn.click(function () {
        StartPause();
    });

    randombtn.click(function () {
        if (!locked) {
            for (var i = 0; i < 10; i++) {
                var per = 0.6;
                var x = Math.floor(Math.random() * paper.width);
                var y = Math.floor(Math.random() * paper.height);
                x = Math.floor(x * per + ((1.0 - per) / 2.0) * paper.width);
                y = Math.floor(y * per + ((1.0 - per) / 2.0) * paper.height);
                addPointAnim(new Point(x, y, null));
            }
        }
    });
    clearbtn.click();
    randombtn.click();
};

function CHAlgorith(points, auxF) {
    this.States = {
        SORTING: 's',
        MOVING: 'm',
        VERIFYING: 'v',
        DONE: 'd'
    };
    this.points = points;
    this.auxF = auxF;
    this.toTheRight = true;
    this.state = this.States.SORTING;
    this.i = 0;
    this.ConvexHull = [];
    this.p = this.q = this.r = null;

    this.convexSort = function () {
        this.points = this.points.sort(function (a, b) {
            if (a.x - b.x == 0) {
                return b.y - a.y;
            }
            return a.x - b.x;
        });
    }

    this.rightSide = function (p, q, r) {
        var D = p.x * q.y - p.x * r.y - p.y * q.x + p.y * r.x + q.x * r.y - q.y * r.x;
        return D > 0;
    }

    this.addToHull = function (index) {
        var p = this.points[index];
        this.ConvexHull.push(p);
        this.auxF.addPointToList(p);
        var n = this.ConvexHull.length;
        if (this.auxF) {
            if (n > 1) {
                this.auxF.lineAnim(this.ConvexHull[n - 2], this.ConvexHull[n - 1]);
            }
        }
    }

    this.removeFromHull = function () {
        var n = this.ConvexHull.length;
        var p = this.ConvexHull[n - 2];
        var paux=this.ConvexHull[n-1];
        $('li', PointsInCH).last().remove()
        $('li', PointsInCH).last().remove()
        this.auxF.addPointToList(paux);
        this.ConvexHull.splice(n - 2, 1);
        n--;
        if (this.auxF) {
            this.auxF.deleteLineAnim(p);
            this.auxF.deleteLineAnim(this.ConvexHull[n - 2]);
            this.auxF.lineAnim(this.ConvexHull[n - 2], this.ConvexHull[n - 1]);
        }
    }

    this.iterate = function () {
        switch (this.state) {
            case this.States.DONE:
                return false;

            case this.States.SORTING:
                this.convexSort();
                this.state=this.States.MOVING;
                return this.iterate();

            case this.States.MOVING:
                if (this.i == 0 && this.toTheRight) {
                    if (this.points.length > 0) {
                        this.addToHull(0);
                        if (this.points.length > 1) {
                            this.addToHull(1);
                        }
                    }
                    this.i = 1;
                    if (this.points.length <= 2) {
                        this.state=this.States.DONE;
                    }
                    return this.state != this.States.DONE;
                }

                if (this.toTheRight) {
                    this.i++;
                    if (this.i >= this.points.length) {
                        this.i = this.points.length - 2;
                        this.toTheRight = false;
                        this.addToHull(this.i);
                    }
                }

                if (!this.toTheRight) {
                    this.i--;
                    if (this.i == -1) {
                        this.ConvexHull.splice(this.ConvexHull.length - 1);
                        this.state=this.States.DONE;
                        $('li', PointsInCH).last().remove()
                        return false;
                    }
                }

                this.addToHull(this.i);
                this.state=this.States.VERIFYING;
                this.r = this.ConvexHull[this.ConvexHull.length - 1];
                return true;

            case this.States.VERIFYING:
                n = this.ConvexHull.length;
                if (n <= 2) {
                    this.state=this.States.MOVING;
                    return this.iterate();
                }
                this.p = this.ConvexHull[n - 3];
                this.q = this.ConvexHull[n - 2];
                if (!this.rightSide(this.p, this.q, this.r)) {
                    this.removeFromHull();
                    return true;
                }
                this.state=this.States.MOVING;
                return this.iterate();
        }
        return true;
    }
}

