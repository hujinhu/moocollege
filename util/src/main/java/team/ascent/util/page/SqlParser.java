package team.ascent.util.page;

import java.util.List;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcUtils;

/**
 * 解析select 包装成 count
 * 
 * @author iacdp
 *
 */
public class SqlParser {

	/**
	 * TODO： check 只测试了简单的sql 转解析 需要测试复杂一点的
	 * 
	 * @param sql
	 * @return
	 */
	public static String parseSelectToCount(String sql) {
		StringBuffer select = new StringBuffer();
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();

		// parser得到AST
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(
				sql, JdbcUtils.MYSQL);
		List<SQLStatement> stmtList = parser.parseStatementList(); //

		// 将AST通过visitor输出
		SQLASTOutputVisitor visitor = SQLUtils.createFormatOutputVisitor(from,
				stmtList, JdbcUtils.MYSQL);
		SQLASTOutputVisitor whereVisitor = SQLUtils.createFormatOutputVisitor(
				where, stmtList, JdbcUtils.MYSQL);
		for (SQLStatement stmt : stmtList) {
			// stmt.accept(visitor);
			if (stmt instanceof SQLSelectStatement) {
				SQLSelectStatement sstmt = (SQLSelectStatement) stmt;
				SQLSelect sqlselect = sstmt.getSelect();
				SQLSelectQueryBlock query = (SQLSelectQueryBlock) sqlselect
						.getQuery();

				query.getFrom().accept(visitor);
				query.getWhere().accept(whereVisitor);
			}
		}

		return "select count(1) from " + from.toString() + " where "
				+ where.toString();
	}

	public static void main(String[] args) {
		String sql = "select t1.id,t1.title as titleName,t1.createtime,t1.content,t1.company_id as companyId,t1.company_ids"
				+ " as companyIds ,(select count(1) from admin_news_read_log a where content_id=t1.id and a.company_id='2657')"
				+ " as readTimes from admin_news t1 where t1.deletflag=0 and t1.type=1 order by t1.createtime desc"
				+ " limit 0,10 ";

		System.out.println(parseSelectToCount(sql));
		;
	}
}
