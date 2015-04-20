<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="js/jqwidgets-ver3.2.2/jqwidgets/styles/jqx.base.css" type="text/css" />
<!--    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/scripts/gettheme.js"></script>-->
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/scripts/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/jqwidgets/jqxpanel.js"></script>
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/jqwidgets/jqxtree.js"></script>
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="js/jqwidgets-ver3.2.2/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var theme = "";

            // Create jqxTree
            $('#jqxTree').jqxTree({ height: '400px', width: '300px', theme: theme });
            var contextMenu = $("#jqxMenu").jqxMenu({ width: '120px', theme: theme, height: '56px', autoOpenPopup: false, mode: 'popup' });
            var clickedItem = null;

            var attachContextMenu = function () {
                // open the context menu when the user presses the mouse right button.
                $("#jqxTree li").on('mousedown', function (event) {
                    var target = $(event.target).parents('li:first')[0];
                    var items = $('#jqxTree').jqxTree('getItems');
                    var itemFlag = false;
                    for (var i = 0; i < items.length; i++) {
                        var currentItem = items[i];
                        if (target.id == currentItem.id && currentItem.level == 2) {
                            itemFlag = true;
                            break;
                        };
                    };
                    var rightClick = isRightClick(event);
                    if (rightClick && itemFlag == true) {
                        $("#jqxTree").jqxTree('selectItem', target);
                        var scrollTop = $(window).scrollTop();
                        var scrollLeft = $(window).scrollLeft();

                        contextMenu.jqxMenu('open', parseInt(event.clientX) + 5 + scrollLeft, parseInt(event.clientY) + 5 + scrollTop);
                        return false;
                    }
                });
            }
            attachContextMenu();
            $("#jqxMenu").on('itemclick', function (event) {
                var item = $.trim($(event.args).text());
                switch (item) {
                    case "Add Item":
                        var selectedItem = $('#jqxTree').jqxTree('selectedItem');
                        if (selectedItem != null) {
                            $('#jqxTree').jqxTree('addTo', { label: 'Item' }, selectedItem.element);
                            attachContextMenu();
                        }
                        break;
                    case "Remove Item":
                        var selectedItem = $('#jqxTree').jqxTree('selectedItem');
                        if (selectedItem != null) {
                            $('#jqxTree').jqxTree('removeItem', selectedItem.element);
                            attachContextMenu();
                        }
                        break;
                }
            });

            // disable the default browser's context menu.
            $(document).on('contextmenu', function (e) {
                if ($(e.target).parents('.jqx-tree').length > 0) {
                    return false;
                }
                return true;
            });

            function isRightClick(event) {
                var rightclick;
                if (!event) var event = window.event;
                if (event.which) rightclick = (event.which == 3);
                else if (event.button) rightclick = (event.button == 2);
                return rightclick;
            }
        });
    </script>
</head>
<body class='default'>
    <div id='jqxWidget'>
        <div id='jqxTree' style='float: left; margin-left: 20px;'>
            <ul>
                <li id='home' item-selected='true'>Home</li>
                <li item-expanded='true'>Solutions
                    <ul>
                        <li>Education</li>
                        <li>Financial services</li>
                        <li>Government</li>
                        <li>Manufacturing</li>
                        <li>Solutions
                            <ul>
                                <li>Consumer photo and video</li>
                                <li>Mobile</li>
                                <li>Rich Internet applications</li>
                                <li>Technical communication</li>
                                <li>Training and eLearning</li>
                                <li>Web conferencing</li>
                            </ul>
                        </li>
                        <li>All industries and solutions</li>
                    </ul>
                </li>
                <li>Products
                    <ul>
                        <li>PC products</li>
                        <li>Mobile products</li>
                        <li>All products</li>
                    </ul>
                </li>
                <li>Support
                    <ul>
                        <li>Support home</li>
                        <li>Customer Service</li>
                        <li>Knowledge base</li>
                        <li>Books</li>
                        <li>Training and certification</li>
                        <li>Support programs</li>
                        <li>Forums</li>
                        <li>Documentation</li>
                        <li>Updates</li>
                    </ul>
                </li>
            </ul>
        </div>
        <div id='jqxMenu'>
            <ul>
                <li>Add Item</li>
                <li>Remove Item</li>
            </ul>
        </div>
    </div>
</body>
</html>