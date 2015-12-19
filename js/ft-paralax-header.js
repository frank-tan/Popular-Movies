$(document).ready(function () {
  $.fn.moveIt = function () {
    var $window = $(window);
    var headerSection;

    headerSection = new moveItItem($(this));

    window.onscroll = function () {
      var scrollTop = $window.scrollTop();
      headerSection.update(scrollTop);
    };
  };

  var moveItItem = function (el) {
    this.el = $(el);
    this.speed = parseInt(this.el.attr('data-scroll-speed'));
  };

  moveItItem.prototype.update = function (scrollTop) {
    var pos = scrollTop / this.speed;
    this.el.css('transform', 'translateY(' + pos + 'px)');
  };

  $(function () {
    $('[data-scroll-speed]').moveIt();
  });
});