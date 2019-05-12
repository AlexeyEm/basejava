$(document).ready(function () {
    $('html').on('click', '.addPosition', function (e) {
        var $id = $(this).data('id');
        var num = parseInt(document.getElementsByName("positions" + $id)[0].value) + 1;
        $ddLast = $('dd[id^=' + $id + ']:last');
        var $clone = $('#initHiddenPosition').eq(0).clone().attr('id', $id);
        $clone.find('input').each(function () {
            this.name = this.name + $id + num;
        });
        $clone.find('button').attr("data-id", $id);
        if ($ddLast.length == 0) {
            $ddLast = $('dt[id=' + $id + ']');
        }
        $ddLast.after($clone);
        $('input[name^=positions' + $id + ']').val(num);
        e.preventDefault();
    });
    $('html').on('click', '.deletePosition', function () {
        var $id = $(this).data('id');
        var num = parseInt(document.getElementsByName("positions" + $id)[0].value) - 1;
        $('input[name^=positions' + $id + ']').val(num);
        $(this).closest(".position").remove();
        var current = 1;
        $('#' + $id).find('dd').each(function () {
            $(this).find('input[class^=position]').each(function () {
                $(this).attr('name', $(this).attr('class') + $id + current);
            });
            current++;
        });
    });
    $('html').on('click', '.addOrganization', function (e) {
        var $id = $(this).data('id');
        var num = parseInt(document.getElementsByName($id)[0].value) + 1;
        $ddLast = $('div[id^=' + $id + ']:last');
        var $clone = $('#initHiddenOrganization').eq(0).clone().attr('id', $id + num).attr('class', 'organization');
        $clone.find("dt").attr("id", $id + num);
        $clone.find('input').each(function () {
            if (this.type == "hidden") {
                this.name = "positions" + $id + num;
            } else {
                this.name = this.name + $id + num;
            }
        });
        $clone.find('button').attr("data-id", $id + num);
        if ($ddLast.length == 0) {
            $ddLast = $('input[name=' + $id + ']');
            $ddLast.before($clone);
        } else {
            $ddLast.after($clone);
        }
        $('input[name=' + $id + ']').val(num);
        e.preventDefault();
    });
    $('html').on('click', '.deleteOrganization', function () {
        var $type = $(this).data('type');
        var num = parseInt(document.getElementsByName($type)[0].value) - 1;
        $('input[name^=' + $type + ']').val(num);
        $(this).closest(".organization").remove();

        var numOrg = 1;
        $('dd[id^=' + $type).find('div[id^=' + $type + ']').each(function () {
            $(this).attr('id', $type + numOrg);
            $(this).find("dt").attr("id", $type + numOrg).each(function () {
                $(this).find('input[class^=organization]').each(function () {
                    $(this).attr('name', $(this).attr('class') + $type + numOrg);
                });
            });
            var numPos = 1;
            $(this).find("dd").attr("id", $type + numOrg).each(function () {
                $(this).find('input[class^=position]').each(function () {
                    $(this).attr('name', $(this).attr('class') + $type + numOrg + numPos);
                });
                numPos++;
            });
            numPos = 1;
            $(this).find("button").attr("data-id", $type + numOrg);
            $(this).find("input[class=positionsEDUCATION]").attr("name", "positions" + $type + numOrg);
            numOrg++;
        });
    });
});