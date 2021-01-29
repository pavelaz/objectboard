<tr>
    <td><input type="checkbox"></td>
    <td style="display: none">+</td>
    <td style="display: none">+</td>
    <td style="text-align: center">0</td>
    <td style="display: none" class="bussinessUnitBuBisCode"><jstl:out value="${ CompanyNumber }">Lost Value</jstl:out></td>
    <td style="table-layout: fixed" contentEditable>New Description</td>
    <td>
        <select style="height: 43px;border-top:none;border-right:none;border-left:none;width: 100%;" class="form-control">
            <jstl:forTokens var="i" items="${ CommentsList }" delims=",">
                <option value="${ i }" ${ commentsDto.commentType == i ? 'selected="selected"': ' '}><jstl:out value="${ i }"/></option>
            </jstl:forTokens>
        </select>
    </td>
</tr>