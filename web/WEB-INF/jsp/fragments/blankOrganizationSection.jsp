<%@page contentType="text/html" pageEncoding="UTF-8" %>
<table border="0">
    <tr>
        <th align="left">Организация:</th>
        <td align="left" colspan="2"><input type="text" name="organizationName${param.type}1"
                                            value="">
        </td>
    </tr>
    <tr>
        <td align="left">Сайт:</td>
        <td align="left" colspan="2"><input type="text" name="organizationUrl${param.type}1"
                                            value="">
        </td>
    </tr>
    <tr>
        <td></td>
        <td>Дата начала:</td>
        <td><input type="date" name="positionBegin${param.type}11" size=30
                   value="3000-01-01">
        </td>
    </tr>
    <tr>
        <td></td>
        <td>Дата окончания:</td>
        <td><input type="date" name="positionEnd${param.type}11" size=30
                   value="3000-01-01">
        </td>
    </tr>
    <tr>
        <td></td>
        <td>Позиция:</td>
        <td><input type="text" name="positionName${param.type}11" size=30
                   value="">
        </td>
    </tr>
    <tr>
        <td></td>
        <td>Описание:</td>
        <td><input type="text" name="positionDesc${param.type}11" size=30
                   value="">
        </td>
    </tr>
</table>
<%--<hr>--%>
<input type="hidden" name="positions${param.type}1" value="1">
<input type="hidden" name="${param.type}" value="1">