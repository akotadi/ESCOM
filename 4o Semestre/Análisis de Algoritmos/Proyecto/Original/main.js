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

// Currently line_added, line_removed, direction_changed, and finished events are handled
function ConvexHullAlgorithm(points, events) {
    this.States = {
        STARTED: 'started',  // Points need to be sorted
        MOVING: 'moving',  // Move right or left and add one more point to the convex hull
        VERIFYING_POINT: 'veriyfing-point', // verify the added point was right turn
        DONE: 'done'
    };
    this.points = points;
    this.events = events;
    this.moving_right = true;
    this.state = this.States.STARTED;
    this.i = 0;
    this.convex_hull = [];
    this.p = this.q = this.r = null;


    this.convex_hull_sort = function () {
        this.points = this.points.sort(function (a, b) {
            dx = a.x - b.x;
            if (dx == 0) {
                return b.y - a.y;
            }
            return dx;
        });
    }

    this.change_state = function (new_state) {
        console.log('State change ' + this.state + '->' + new_state);
        this.state = new_state;
    }


    // Use a determinant to determine if point r is right of the directed line pq
    this.is_right_of_line = function (p, q, r) {
        var D = p.x * q.y - p.x * r.y - p.y * q.x + p.y * r.x + q.x * r.y - q.y * r.x;
        return D > 0;
    }

    this.add_point_to_hull = function (index) {
        console.log('Adding #' + index.toString());
        var p = this.points[index];
        this.convex_hull.push(p);
        var n = this.convex_hull.length;
        if (this.events) {
            if (n > 1) {
                this.events.line_added(this.convex_hull[n - 2], this.convex_hull[n - 1]);
            }
        }
    }

    this.remove_point_from_hall = function () {
        console.log('Removing point.');
        var n = this.convex_hull.length;
        var p = this.convex_hull[n - 2];
        this.convex_hull.splice(n - 2, 1);
        n--;
        if (this.events) {
            this.events.line_removed(p);
            this.events.line_removed(this.convex_hull[n - 2]);
            this.events.line_added(this.convex_hull[n - 2], this.convex_hull[n - 1]);
        }
    }

    // The main part of the algorithm is done here
    // Returns false when done
    // This way you can do while(ch.step()); to go through all the steps
    this.step = function () {
        switch (this.state) {
            case this.States.DONE:
                return false;

            case this.States.STARTED:
                this.convex_hull_sort();
                this.change_state(this.States.MOVING);
                // We do not want the sort to count as a pausing step 
                // so let's call step again
                return this.step();

            case this.States.MOVING:
                // Add first and second points to the hull
                if (this.i == 0 && this.moving_right) {
                    if (this.points.length > 0) {
                        this.add_point_to_hull(0);
                        if (this.points.length > 1) {
                            this.add_point_to_hull(1);
                        }
                    }
                    this.i = 1;
                    if (this.points.length <= 2) {
                        this.change_state(this.States.DONE);
                    }
                    return this.state != this.States.DONE;
                }

                if (this.moving_right) {
                    this.i++;
                    if (this.i >= this.points.length) {
                        // Change direction
                        if (this.events) {
                            this.events.direction_changed();
                        }
                        console.log('Changing direction');
                        this.i = this.points.length - 2;
                        this.moving_right = false;
                        this.add_point_to_hull(this.i);
                    }
                }

                // Moving left
                if (!this.moving_right) {
                    this.i--;
                    if (this.i == -1) {
                        // We are done. Remove last item since it's the same as first.
                        this.convex_hull.splice(this.convex_hull.length - 1);
                        this.change_state(this.States.DONE);
                        if (this.events) {
                            this.events.finished(this.convex_hull);
                        }
                        return false;
                    }
                }

                this.add_point_to_hull(this.i);
                this.change_state(this.States.VERIFYING_POINT);
                this.r = this.convex_hull[this.convex_hull.length - 1];
                return true;

            case this.States.VERIFYING_POINT:
                n = this.convex_hull.length;
                if (n <= 2) {
                    this.change_state(this.States.MOVING);
                    return this.step();
                }
                this.p = this.convex_hull[n - 3];
                this.q = this.convex_hull[n - 2];
                if (!this.is_right_of_line(this.p, this.q, this.r)) {
                    this.remove_point_from_hall();
                    return true;
                }
                this.change_state(this.States.MOVING);
                return this.step();
        }
        return true;
    }
}
window.point_color = '#131723';
window.line_color_going_right = '#DD577A';
window.line_color_going_left = '#FE5A27';
window.background_color = '#FFF0CF';
window.convex_hull_point_color = '#49AEC0';
window.current_line_color = window.line_color_going_right;

window.onload = function () {
    // Raphael only has a path function. Let's make our own line function.
    Raphael.fn.line = function (startX, startY, endX, endY) {
        return this.path('M' + startX + ' ' + startY + ' L' + endX + ' ' + endY);
    };

    var paper = Raphael("canvas", 640, 480);
    var clear_btn = $('#clearbtn');
    var step_btn = $('#step');
    var random_btn = $('#random');
    var run_btn = $('#run');
    var points = [];
    var canvas = null;
    var locked = false;
    var ch = null;
    var run_timer = null;

    function visualize_line_added(p, q) {
        line = paper.line(p.x, p.y, p.x, p.y).attr({
            'stroke-linecap': 'round',
            'stroke-linejoin': 'round',
            'stroke': window.current_line_color
        });
        p.main_line = line.animate({
            'stroke-width': '3',
            'path': 'M' + p.x + ' ' + p.y + ' L' + q.x + ' ' + q.y
        }, 200);
    }

    function visualize_line_removed(p, q) {
        p.main_line.attr({
            'stroke-dasharray': '-.'
        }).animate({
            'stroke-width': '1'
        }, 200);
    }

    function visualize_convex_hull(convex_hull) {
        var anim1 = Raphael.animation({
            r: '5',
            fill: window.convex_hull_point_color
        }, 200);
        for (var i = 0; i < convex_hull.length; i++) {
            convex_hull[i].graphic.animate(anim1.delay(i * 200)).toFront();
        }
    }

    function visualize_direction_changed() {
        window.current_line_color = window.line_color_going_left;
    }

    var events = {
        line_added: visualize_line_added,
        line_removed: visualize_line_removed,
        direction_changed: visualize_direction_changed,
        finished: visualize_convex_hull
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

    function add_point(p) {
        if (locked) {
            return;
        }
        c = paper.circle(p.x, p.y, 1).animate({
            r: 3,
            fill: window.point_color,
            "stroke-width": 0
        }, 200);
        p.graphic = c;
        points.push(p);
    }

    function clear() {
        window.current_line_color = window.line_color_going_right;
        paper.clear();
        canvas = paper.rect(0, 0, 640, 480, 10).attr({
            fill: window.background_color,
            stroke: "none"
        });
        points = [];
        unlock();
        running = false;
        ch = null;
        run_btn.text('Run');
        run_btn.attr('disabled', false);
        step_btn.attr('disabled', false);

        canvas.mouseup(function (e) {
            p = getMousePos(e);
            add_point(p);
        });
    }

    function lock() {
        locked = true;
        random_btn.attr('disabled', true);
        if (ch == null) {
            ch = new ConvexHullAlgorithm(points, events);
        }
    }

    function unlock() {
        locked = false;
        random_btn.attr('disabled', false);
    }

    function toggle_run_stop() {
        if (running) {
            running = false;
            window.clearInterval(run_timer);
            run_btn.text('Run');
            run_btn.attr('disabled', false);
            step_btn.attr('disabled', false);
            clear_btn.attr('disabled', false);
        } else {
            running = true;
            lock();
            if (ch == null) {
                ch = new ConvexHullAlgorithm(points, events);
            }
            run_btn.text('Stop');
            clear_btn.attr('disabled', true);
            step_btn.attr('disabled', true);
            run_timer = window.setInterval(function () {
                r = ch.step();
                if (!r) {
                    window.clearInterval(run_timer);
                    run_btn.text('Done');
                    run_btn.attr('disabled', true);
                    step_btn.attr('disabled', true);
                    clear_btn.attr('disabled', false);
                }
            }, 300);
        }
    }

    clear_btn.click(function () {
        clear();
    });
    clear_btn.click();


    step_btn.click(function () {
        lock();
        if (!ch.step()) {
            run_btn.text('Done');
            run_btn.attr('disabled', true);
            step_btn.attr('disabled', true);
            clear_btn.attr('disabled', false);
        }
    });

    run_btn.click(function () {
        toggle_run_stop();
    });

    random_btn.click(function () {
        if (!locked) {
            for (var i = 0; i < 10; i++) {
                var per = 0.6;
                var x = Math.floor(Math.random() * paper.width);
                var y = Math.floor(Math.random() * paper.height);
                x = Math.floor(x * per + ((1.0 - per) / 2.0) * paper.width);
                y = Math.floor(y * per + ((1.0 - per) / 2.0) * paper.height);
                add_point(new Point(x, y, null));
            }
        }
    });

    random_btn.click();

};